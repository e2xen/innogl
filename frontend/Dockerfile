FROM node:16-alpine

ARG REACT_APP_BASE_URL
WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH
ENV REACT_APP_BASE_URL=$REACT_APP_BASE_URL

COPY package.json ./
COPY package-lock.json ./
RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent

COPY . ./

EXPOSE 3000
CMD ["npm", "start"]