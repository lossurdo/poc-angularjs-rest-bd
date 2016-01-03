var restify = require('restify');

var server = restify.createServer();

/*
	CRUD GÊNERO =============================
*/
server.get('/seriado-model/rest/generos', function (req, res, next) {
	var json = [{"id":1,"genero":"Adventure"},{"id":2,"genero":"Sci-Fi"},{"id":3,"genero":"Crime"},{"id":4,"genero":"Fantasy"},{"id":5,"genero":"Drama"},{"id":5,"genero":"Mystery"},{"id":5,"genero":"Thriller"},{"id":5,"genero":"Action"}]; 
	res.send(json);
	return next();
});

/*
	CRUD SERIADO =============================
*/
server.get('/seriado-model/rest/seriados', function (req, res, next) {
	var json = [{"id":1,"titulo":"Game of Thrones","sumario":"Several noble families fight for control of the mythical land of Westeros.","ano":2011,"nota":9.5,"criadores":"David Benioff, D.B. Weiss","elenco":"Peter Dinklage, Lena Headey, Emilia Clarke, Kit Harington","finalizado":false,"generoList":[{"id":1,"genero":"Adventure"},{"id":5,"genero":"Drama"},{"id":4,"genero":"Fantasy"}]},{"id":2,"titulo":"Homeland","sumario":"When Marine Nicolas Brody is hailed as a hero after he returns home from eight years of captivity in Iraq, intelligence officer Carrie Mathison is the only one who suspects that he may have been turned.","ano":2011,"nota":8.4,"criadores":"Alex Gansa, Howard Gordon","elenco":"Claire Danes, Mandy Patinkin, Rupert Friend, Damian Lewis","finalizado":false,"generoList":[{"id":5,"genero":"Drama"},{"id":5,"genero":"Mystery"},{"id":5,"genero":"Thriller"}]},{"id":3,"titulo":"The X-Files","sumario":"Two FBI agents, Fox Mulder the believer and Dana Scully the skeptic, investigate the strange and unexplained while hidden forces work to impede their efforts.","ano":1993,"nota":8.7,"criadores":"Chris Carter","elenco":"Gillian Anderson, David Duchovny, Mitch Pileggi","finalizado":true,"generoList":[{"id":5,"genero":"Drama"},{"id":5,"genero":"Mystery"},{"id":2,"genero":"Sci-Fi"}]}]; 
	res.send(json);
	return next();
});


/*
server.get('/seriado-model/rest/generos/:id', respond);
server.put('/seriado-model/rest/generos/:id', respond);
server.post('/seriado-model/rest/generos/:id', respond);
server.del('/seriado-model/rest/generos/:id', respond);
*/

server.listen(8080, function() {
	console.log('%s listening at %s', server.name, server.url);
});