from flask import Flask, jsonify
from flask_sqlalchemy import SQLAlchemy
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

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

# 코사인 유사도 계산 함수
def calculate_cosine_similarity(liked_crop, crops):
    tfidf_vectorizer = TfidfVectorizer()

    # 농작물 데이터를 피처로 벡터화
    crop_features = [crop["temperature"] + crop["sunshine"] + crop["water_period"] for crop in crops]
    liked_crop_features = liked_crop["temperature"] + liked_crop["sunshine"] + liked_crop["water_period"]
    crop_features.append(liked_crop_features)

    # 벡터화된 피처들에 대해 코사인 유사도 계산
    tfidf_matrix = tfidf_vectorizer.fit_transform(crop_features)
    cosine_similarities = cosine_similarity(tfidf_matrix, tfidf_matrix)

    # 코사인 유사도가 가장 높은 농작물 추천
    similar_crops_indices = cosine_similarities[-1].argsort()[:-2:-1]  # 가장 유사도가 높은 농작물의 인덱스

    # 추천된 농작물이 없는 경우 예외 처리
    if not similar_crops_indices:
        return {}

    similar_crop = crops[similar_crops_indices[0]]
    return similar_crop


# API 엔드포인트 정의
@app.route('/recommended_crop', methods=['GET'])
def get_recommended_crop():
    # 좋아요를 누른 농작물 데이터
    liked_crop = {
        "id": 2,
        "name": "감자",
        "temperature": "중",
        "sunshine": "상",
        "water_period": "하"
    }

    # 모든 농작물 데이터 가져오기
    all_crops = Crop.query.all()
    crops = []
    for crop in all_crops:
        crops.append({
            "id": crop.id,
            "name": crop.name,
            "temperature": crop.temperature,
            "sunshine": crop.sunshine,
            "water_period": crop.water_period
        })

    # 코사인 유사도를 사용하여 추천 농작물 계산
    recommended_crop = calculate_cosine_similarity(liked_crop, crops)

    return jsonify({"recommended_crop": recommended_crop})

# 애플리케이션 실행
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8082, debug=True)
