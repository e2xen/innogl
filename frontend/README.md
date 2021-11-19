# Frontend Innogl
This folder contains frontend part of Innogl project. 


## How to run
### Pre-installation
You should have pre-installed [Docker](https://docs.docker.com/engine/install/).

### Running a project
**Important**: do not forget to run backend before running frontend.

Run the following commands in your terminal:
1. Pull this repo from github and navigate to **innogl/frontend**:
```shell
 cd innogl/frontend
```
2. Export variable to gateway of your backend
(if your backend is running at your machine, then use the following command):
```shell
export BACKEND_GATEWAY=http://localhost:8080/
```
3. Build image
```shell
docker build -t innogl_frontend --build-arg REACT_APP_BASE_URL=$BACKEND_GATEWAY .
```
4. Run the image
```shell
docker run -p 3000:3000 -d --name innogl_frontend
```

Now the application is available in your browser: [http://localhost:3000](http://localhost:3000)!

## Available Scripts
In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.