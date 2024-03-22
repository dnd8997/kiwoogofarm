from flask import Flask
from flask_sqlalchemy import SQLAlchemy

# Flask 애플리케이션 생성
app = Flask(__name__)

# MySQL 데이터베이스 설정
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:ssafy@localhost/member'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

# 데이터베이스 모델 정의
class users(db.Model):
    user_id = db.Column(db.Integer, primary_key=True)
    role = db.Column(db.String(100), nullable=False)
    email = db.Column(db.String(100), nullable=False)

# 라우트 및 API 엔드포인트 정의
@app.route('/users', methods=['GET'])
def get_users():
    user = users.query.all()
    return {'User': [{'user_id': User.user_id, 'role': User.role, 'email': User.email} for User in user]}

# 애플리케이션 실행
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8082, debug=True)

