FROM node:alpine

RUN mkdir /app

ENV PATH /app/node_modules/.bin:$PATH

ENV NODE_ENV production

WORKDIR /app

ADD ./ ./

RUN npm ci
RUN npm run build

CMD ["npx", "serve", "build"]

