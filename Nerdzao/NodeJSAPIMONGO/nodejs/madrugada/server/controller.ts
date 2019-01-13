import * as mongoose from "mongoose";

const CrushSchema = new mongoose.Schema({
    nome : { type: String, required : true},
    apelido : { type: String, required : true},
    whatsapp : { type: String, required : true, unique : true},
    creatAt : { type : Date, default : Date.now }
})

const model = mongoose.model("iotapi", CrushSchema);

class Controller{

    constructor(){}

    create(req, res){

        this.createTrouxa(JSON.parse(req).result)
        .then(dados => res.status(200).json({"resultado" : dados}))
        .catch(err => res.status(400).json({"resultado" : err}));
    }

    createTrouxa(data){
        return model.create(data);
    }

    getDados(req, res){
        this.getTrouxas()
        .then(dados => res.status(200).json({"resultado" : dados}))
        .catch(err => res.status(400).json({"resultado" : err}));   
    }

    getTrouxas(){
        return model.find({});
    }

    deleteDado(req, res){
        const id = {_id : req.params.id}

        this.deleteTrouxas(id)
        .then(dados => res.status(200).json({"resultado" : dados}))
        .catch(err => res.status(400).json({"resultado" : err}));   
    }

    deleteTrouxas(id){
        return model.deleteOne(id);
    }

    getDadosOne(req, res){
        const id = {_id : req.params.id}
        this.getTrouxaOne(id)
        .then(dados => res.status(200).json({"resultado" : dados}))
        .catch(err => res.status(400).json({"resultado" : err})); 
    }

    getTrouxaOne(id){
        return model.find(id);
    }

    putDado(req, res){
        const id = {_id : req.params.id}
        const crush = req.body;

        this.updateDado(id, crush)
        .then(dados => res.status(200).json({"resultado" : dados}))
        .catch(err => res.status(400).json({"resultado" : err})); 
    }

    updateDado(id, crush){
        return model.findOneAndUpdate(id, crush);
    }

    createDados(req, res){
        const corpo = req.body;
        this.createTrouxa(corpo)
        .then(dados => res.status(200).json({"resultado" : dados}))
        .catch(err => res.status(400).json({"resultado" : err})); 
    }


}


export default Controller;