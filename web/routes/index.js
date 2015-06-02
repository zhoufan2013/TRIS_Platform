var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

/* GET home page. */
router.get('/upc_execute', function(req, res, next) {
    res.render('upc/execute', { title: 'Express' });
});

module.exports = router;
