FROM node:current-alpine

RUN mkdir /app

ENV PATH /app/node_modules/.bin:$PATH


WORKDIR /app

COPY ["package.json", "package-lock.json*", "./"]

EXPOSE 3000

RUN npm install -g npm@9.1.2
RUN npm install 

ADD ./ ./

CMD ["npm","start"]

