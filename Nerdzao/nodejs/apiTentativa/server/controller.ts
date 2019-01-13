import * as mongoose from 'mongoose';

const IoTSchema = new mongoose.Schema({
    horario_de_entrada : { type: String, required : true},
    presenca : { type: String , required : true},
    horario_de_saida : { type: String, required : true, unique : true},
    creatAt : { type : Date, default : Date.now }
})

const model = mongoose.model("iotapi", IoTSchema);

class Controller{

    constructor(){}

    refresh(req, res){
        this.createDado(JSON.parse(req))
        .then(dados => res.status(200).json({"resultado" : dados}))
        .catch(err => res.status(400).json({"resultado" : err}));
    }

    createDado(data){
        return model.create(data);
    }

    buscaUltimo(req, res){
        this.getUltimo()
        .then(dados => res.status(200).json({"resultado" : dados}))
        .catch(err => res.status(400).json({"resultado" : err}));  
    }

    getUltimo(){
        return model.findOne().sort({ field: 'asc', _id: -1 });
    }

    buscaTodos(req, res){
        this.getTodos()
        .then(dados => res.status(200).json({"resultado" : dados}))
        .catch(err => res.status(400).json({"resultado" : err}));  
    }


    getTodos(){
        return model.find({});
    }

}

export default Controller;