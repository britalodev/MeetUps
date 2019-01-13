import * as mongoose from 'mongoose';

class DataBase{
    private url = 'mongodb://127.0.0.1/iot';
    private connection = mongoose.connection;

    constructor(){}

    createConnection(){
        mongoose.connect(this.url);
        this.connection.on("connected", () => console.log("mongoose esta conectado"));

        this.connection.on("error", error => console.error.bind(console, "Erro na conexão :" + error));
    }

    closeConnection(message, callback){
        this.connection.close(() => console.log("Mongoose foi desconectado"));
    }
}

export default DataBase;