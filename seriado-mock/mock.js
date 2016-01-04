var restify = require('restify');

var server = restify.createServer();
server
	.use(restify.fullResponse())
	.use(restify.bodyParser())
	.use(restify.CORS());

/*
	DADOS GENÉRICOS INICIAIS =============================
*/
var objGenero = [{"id":1,"genero":"Adventure"},{"id":2,"genero":"Sci-Fi"},{"id":3,"genero":"Crime"},{"id":4,"genero":"Fantasy"},{"id":5,"genero":"Drama"},{"id":5,"genero":"Mystery"},{"id":5,"genero":"Thriller"},{"id":5,"genero":"Action"}]; 
var objSeriado = [{"id":1,"titulo":"Game of Thrones","sumario":"Several noble families fight for control of the mythical land of Westeros.","ano":2011,"nota":9.5,"criadores":"David Benioff, D.B. Weiss","elenco":"Peter Dinklage, Lena Headey, Emilia Clarke, Kit Harington","finalizado":false,"generoList":[{"id":1,"genero":"Adventure"},{"id":5,"genero":"Drama"},{"id":4,"genero":"Fantasy"}]},{"id":2,"titulo":"Homeland","sumario":"When Marine Nicolas Brody is hailed as a hero after he returns home from eight years of captivity in Iraq, intelligence officer Carrie Mathison is the only one who suspects that he may have been turned.","ano":2011,"nota":8.4,"criadores":"Alex Gansa, Howard Gordon","elenco":"Claire Danes, Mandy Patinkin, Rupert Friend, Damian Lewis","finalizado":false,"generoList":[{"id":5,"genero":"Drama"},{"id":5,"genero":"Mystery"},{"id":5,"genero":"Thriller"}]},{"id":3,"titulo":"The X-Files","sumario":"Two FBI agents, Fox Mulder the believer and Dana Scully the skeptic, investigate the strange and unexplained while hidden forces work to impede their efforts.","ano":1993,"nota":8.7,"criadores":"Chris Carter","elenco":"Gillian Anderson, David Duchovny, Mitch Pileggi","finalizado":true,"generoList":[{"id":5,"genero":"Drama"},{"id":5,"genero":"Mystery"},{"id":2,"genero":"Sci-Fi"}]}]; 

/*
	CRUD GÊNERO =============================
*/
server.get('/seriado-model/rest/generos', function (req, res, next) {
  	res.send(objGenero);
	return next();
});
server.get('/seriado-model/rest/generos/search/:obj', function (req, res, next) {
	res.send(objGenero);
	return next();
});
server.post('/seriado-model/rest/generos', function (req, res, next) {
	var id = Math.random().toString().substr(3, 5);
	var obj = req.body;
	obj.id = id;
	objGenero.push(obj);
    res.header('Location', '/seriado-model/rest/generos/' + id);	
	res.send(201);
	return next();
});
server.del('/seriado-model/rest/generos/:id', function (req, res, next) {
	var id = req.params.id;

	var found = false;
	for(var i=0;i<objGenero.length;i++) {
		if(objGenero[i].id==id) {
			found = true;
			objGenero.splice(i, 1);
		}
	}

	if(found) {res.send(200); } else {res.send(404);}
	return next();
});
server.get('/seriado-model/rest/generos/:id', function (req, res, next) {
	var id = req.params.id;

	var obj = null;
	var found = false;
	for(var i=0;i<objGenero.length;i++) {
		if(objGenero[i].id==id) {
			found = true;
			obj = objGenero[i];
		}
	}

	if(found) {res.send(obj);} else {res.send(404);}
	return next();
});
server.put('/seriado-model/rest/generos', function (req, res, next) {	
	var obj = req.body;

	var found = false;
	for(var i=0;i<objGenero.length;i++) {
		if(objGenero[i].id==obj.id) {
			found = true;
			objGenero.splice(i, 1);
		}
	}

	if(found) {
		objGenero.push(obj);
	    res.header('Location', '/seriado-model/rest/generos/' + obj.id);	
		res.send(200);
	} else {
		res.send(404);
	}

	return next();
});


/*
	CRUD SERIADO =============================
*/
server.get('/seriado-model/rest/seriados', function (req, res, next) {
	res.send(objSeriado);
	return next();
});

server.use(
  function crossOrigin(req,res,next){
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    return next();
  }
);

server.listen(8080, function() {
	console.log('%s ouvindo em %s', server.name, server.url);
});