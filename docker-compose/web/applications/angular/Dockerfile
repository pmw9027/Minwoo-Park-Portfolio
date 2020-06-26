FROM node:12.7-alpine AS build
WORKDIR /usr/src/app
COPY data/portal-app/package.json .
RUN npm install

COPY data/portal-app/. .
RUN npm run build


FROM nginx:1.17.1-alpine
COPY --from=build /usr/src/app/dist/portal-app /usr/share/nginx/html
