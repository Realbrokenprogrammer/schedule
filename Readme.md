# schedule

Schedule

## Requirements

* [dotnet SDK](https://www.microsoft.com/net/download/core) 2.0.0 or higher
* [node.js](https://nodejs.org) 4.8.2 or higher
* npm5: JS package manager

## Running the app

* Install JS dependencies: `npm install`
* **Move to `src` folder**: `cd src`
* Install F# dependencies: `dotnet restore`
* Start Fable daemon and [Webpack](https://webpack.js.org/) dev server: `dotnet fable npm-start`
* In your browser, open: http://localhost:8080/

# Building the app

* Install JS dependencies: `npm install`
* **Move to `src` folder**: `cd src`
* Install F# dependencies: `dotnet restore`
* Build Fable daemon and [Webpack](https://webpack.js.org/): `dotnet fable npm-build`
* Navigate to `public\index.html` and open it.