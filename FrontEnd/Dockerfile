FROM node:20.11.0

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

RUN npm run build

CMD ["node", "server.js"]  # 예시: 서버 파일의 이름이 server.js인 경우
