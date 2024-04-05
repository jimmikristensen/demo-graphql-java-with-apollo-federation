FROM node:14.21.0-alpine

COPY package*.json ./
COPY server.js ./
COPY wait ./

RUN chmod +x ./wait
RUN apk --no-cache add curl
RUN npm install

EXPOSE 4000
CMD /wait && npm run start