from flask import Flask, jsonify
from flask_sqlalchemy import SQLAlchemy
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import numpy as np

# Flask 애플리케이션 생성
app = Flask(__name__)

# MySQL 데이터베이스 설정
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:ssafy@j10b303.p.ssafy.io/member'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

# 데이터베이스 모델 정의
class Crop(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), nullable=False)
    temperature = db.Column(db.String(100), nullable=False)
    sunshine = db.Column(db.String(100), nullable=False)
    water_period = db.Column(db.String(100), nullable=False)
    difficulty = db.Column(db.String(100), nullable=False)
    grow_time = db.Column(db.String(100), nullable=False)
    humidity = db.Column(db.String(100), nullable=False)
    grow_start = db.Column(db.String(100), nullable=False)
    water_exit = db.Column(db.String(100), nullable=False)


def string_to_number(value):
    if value == "상":
        return 3
    elif value == "중":
        return 2
    elif value == "하":
        return 1
    elif value == "최하":
        return 0
    else:
        raise ValueError("Invalid value: {}".format(value))


def difficulty_to_number(value):
    if value == "어려움":
        return 3
    elif value == "보통":
        return 2
    elif value == "쉬움":
        return 1
    else:
        raise ValueError("Invalid value: {}".format(value))


def season_to_number(value):
    if value == "봄, 가을":
        return 4 + 2
    elif value == "봄":
        return 4
    elif value == "여름":
        return 3
    elif value == "가을":
        return 2
    elif value == "겨울":
        return 1
    elif value == "무관":
        return 0
    else:
        raise ValueError("Invalid value: {}".format(value))



# 코사인 유사도 계산 함수
def calculate_cosine_similarity(liked_crops, crops):
    liked_crop_features = np.array([[string_to_number(liked_crop["temperature"]),
                                     string_to_number(liked_crop["sunshine"]),
                                     string_to_number(liked_crop["water_period"]),
                                     difficulty_to_number(liked_crop["difficulty"]),
                                     string_to_number(liked_crop["grow_time"]),
                                     string_to_number(liked_crop["humidity"]),
                                     season_to_number(liked_crop["grow_start"]),
                                     string_to_number(liked_crop["water_exit"])]
                                    for liked_crop in liked_crops])

    liked_crop_ids = set(liked_crop["id"] for liked_crop in liked_crops)
    # 좋아요를 누른 작물의 피처를 벡터화
    crops_features = np.array([[string_to_number(crop["temperature"]),
                                string_to_number(crop["sunshine"]),
                                string_to_number(crop["water_period"]),
                                difficulty_to_number(crop["difficulty"]),
                                string_to_number(crop["grow_time"]),
                                string_to_number(crop["humidity"]),
                                season_to_number(crop["grow_start"]),
                                string_to_number(crop["water_exit"])]
                                for crop in crops if crop["id"] not in liked_crop_ids])

        # 각 작물의 벡터와 좋아요를 누른 작물들의 벡터 사이의 코사인 유사도 계산
    cosine_similarities = cosine_similarity(liked_crop_features, crops_features)

    similar_crops_indices = np.argsort(cosine_similarities, axis=1)[:, ::-1]
    unique_indices = []
    for indices in similar_crops_indices:
        for index in indices:
            if index not in unique_indices:
                unique_indices.append(index)
            if len(unique_indices) == 3:  # 중복을 제거한 후 상위 3개의 작물만 추천
                break
        if len(unique_indices) == 3:
            break

    recommended_crops = [crops[index] for index in unique_indices]

    return recommended_crops


def calculate_euclidean_distance(liked_crops, crops):
    liked_crop_features = np.array([[string_to_number(liked_crop["temperature"]),
                                     string_to_number(liked_crop["sunshine"]),
                                     string_to_number(liked_crop["water_period"]),
                                     difficulty_to_number(liked_crop["difficulty"]),
                                     string_to_number(liked_crop["grow_time"]),
                                     string_to_number(liked_crop["humidity"]),
                                     season_to_number(liked_crop["grow_start"]),
                                     string_to_number(liked_crop["water_exit"])]
                                    for liked_crop in liked_crops])

    # 좋아요를 누른 작물의 특성과 crops에 있는 각 작물의 특성 간의 유클리드 거리 계산
    euclidean_distances = []

    liked_crop_ids = set(liked_crop["id"] for liked_crop in liked_crops)
    # 좋아요를 누른 작물의 피처를 벡터화
    for crop in crops:
        if crop["id"] not in liked_crop_ids:
            crop_vector = np.array([string_to_number(crop["temperature"]),
                                    string_to_number(crop["sunshine"]),
                                    string_to_number(crop["water_period"]),
                                    difficulty_to_number(crop["difficulty"]),
                                    string_to_number(crop["grow_time"]),
                                    string_to_number(crop["humidity"]),
                                    season_to_number(crop["grow_start"]),
                                    string_to_number(crop["water_exit"])])
            # 유클리드 거리 계산
            distance = np.linalg.norm(liked_crop_features - crop_vector)
            euclidean_distances.append((crop, distance))

    sorted_distances = sorted(euclidean_distances, key=lambda x: x[1])
    recommended_crops = [crop for crop, _ in sorted_distances[:3]]

    # 작물 간의 유클리드 거리 반환
    return recommended_crops


# API 엔드포인트 정의
@app.route('/crop', methods=['GET'])
def cosine_recommended_crop():
    # 좋아요를 누른 농작물 데이터
    liked_crops = [
        {
            "difficulty": "보통",
            "grow_start": "봄, 가을",
            "grow_time": "상",
            "humidity": "상",
            "id": 20,
            "name": "루끌라",
            "sunshine": "상",
            "temperature": "중",
            "water_exit": "상",
            "water_period": "상"
        }
    ]
    # 모든 농작물 데이터 가져오기
    all_crops = Crop.query.all()
    crops = []
    for crop in all_crops:
        crop.dict ={
            "id": crop.id,
            "name": crop.name,
            "temperature": crop.temperature,
            "sunshine": crop.sunshine,
            "water_period": crop.water_period,
            "difficulty": crop.difficulty,
            "grow_time": crop.grow_time,
            "humidity": crop.humidity,
            "grow_start": crop.grow_start,
            "water_exit": crop.water_exit
        }
        crops.append(crop.dict)

    # 코사인 유사도를 사용하여 추천 농작물 계산
    recommended_crop = calculate_cosine_similarity(liked_crops, crops)

    return jsonify({"recommended_crop": recommended_crop})


@app.route('/crop2', methods=['GET'])
def euclidean_recommended_crop():
    # 좋아요를 누른 농작물 데이터
    liked_crops = [
        {
            "difficulty": "보통",
            "grow_start": "봄, 가을",
            "grow_time": "상",
            "humidity": "상",
            "id": 20,   
            "name": "루끌라",
            "sunshine": "상",
            "temperature": "중",
            "water_exit": "상",
            "water_period": "상"
        }
    ]
    # 모든 농작물 데이터 가져오기
    all_crops = Crop.query.all()
    crops = []
    for crop in all_crops:
        crop.dict ={
            "id": crop.id,
            "name": crop.name,
            "temperature": crop.temperature,
            "sunshine": crop.sunshine,
            "water_period": crop.water_period,
            "difficulty": crop.difficulty,
            "grow_time": crop.grow_time,
            "humidity": crop.humidity,
            "grow_start": crop.grow_start,
            "water_exit": crop.water_exit
        }
        crops.append(crop.dict)

    # 코사인 유사도를 사용하여 추천 농작물 계산
    recommended_crop = calculate_euclidean_distance(liked_crops, crops)

    return jsonify({"recommended_crop": recommended_crop})


# 애플리케이션 실행
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8082, debug=True)
