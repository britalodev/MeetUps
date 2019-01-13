import * as express from 'express';
import * as request from 'request';
import * as bodyParser from 'body-parser';

/* classes criadas  */
import database from './database'
import controller from "./controller";


class App{

    public app : express.Application;
    public database : database;
    public controller : controller;

    constructor(){
        this.app = express();
        this.middleware();
        this.routes();
        this.database = new database();
        this.database.createConnection();
        this.controller = new controller();
    }

    middleware(){
        this.app.use(bodyParser.json());
        this.app.use(bodyParser.urlencoded({ extended: true }));
      }

      routes(){
        this.app.route("/api/refresh").get(this.getEndPoint.bind(this));
        this.app.route("/api/ultimo").get((req, res) => this.controller.buscaUltimo(req, res));
        this.app.route("/api/todos").get((req, res) => this.controller.buscaTodos(req, res));
    }


    getEndPoint(req, res){
        request("https://nodhenrique.mybluemix.net/nossoprojeto", (error, response, body) => this.controller.refresh(body, res));
        
        /*res.status(200).json({"status" : body}));*/
    }
}

export default new App();