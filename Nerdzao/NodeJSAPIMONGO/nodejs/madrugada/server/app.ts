import * as express from 'express';
import * as request from 'request';
import * as bodyParser from 'body-parser';

/* classes criadas */
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
        this.app.route("/").get(  (req, res)  => res.status(200).json({"hello" : "world"}));
        this.app.route("/api/refresh").get(this.getEndPoint.bind(this));
        this.app.route("/api/dados").get((req, res) => this.controller.getDados(req, res));
        this.app.route("/api/dados/:id").get((req, res) => this.controller.getDadosOne(req, res));
        this.app.route("/api/dados/:id").delete((req, res) => this.controller.deleteDado(req, res));
        this.app.route("/api/dados").post((req, res) => this.controller.createDados(req, res));
        this.app.route("/api/dados/:id").put((req, res) => this.controller.putDado(req, res));
        
    }

    getEndPoint(req, res){
        request("https://crush-management.herokuapp.com/api/crushs", (error, response, body) => this.controller.create(body, res));
        
        /*res.status(200).json({"status" : body}));*/
    }

}
export default new App();

