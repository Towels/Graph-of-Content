
'use strict';

//TODO documentation
angular.module('graphOfContentApp')
.factory('graphRenderer', function () {
	var colorChapter = '#58D3F7', //bright blue
		colorSection = '#82FA58', //bright green
	    colorTopic = '#0000FF', //dark blue
	    colorComment ='#FF4000', //orange
	    colorText = '#151515'; // dark grey
	var renderer = {};
	var s;
	var clickNode = function() {};
	
renderer.setClickNode = function(f){
		clickNode = f;
};
	
renderer.setGraphData = function(data){
	if(s != undefined){
		s.kill();
	}
	s = new sigma({
		graph: data,
	    container: 'graph-container',
	    renderer: {
	        container: document.getElementById('graph-container'),
	        type: 'canvas'
	    },
	    settings: {
	        sideMargin: 0.5,
	        drawLabels: false,
	        enableHovering: false
	    }
	});
	s.bind('clickNode', clickNode);
};
renderer.drawEllipse = function(context, color, posX, posY, size, scaleX, scaleY) {
    context.fillStyle = color;
    context.beginPath();
    context.save();
    // scale context horizontally
    context.scale(scaleX, scaleY);
    context.arc(
        posX,
        posY,
        size,
        0,
        Math.PI*2,
        false
    );
    context.fill();
    context.restore();
    context.closePath();
};

renderer.drawFont = function(context, size, label, posX, posY) {
    context.beginPath();
    context.fillStyle = colorText;
    context.font = 'bold ' + size +'px Arial';
    context.fillText(label, posX, posY);

    context.closePath();
};

//node chapter renderer
sigma.canvas.nodes.CHAPTER = function(node, context, settings) {
    var prefix = settings('prefix') || '',
        size = node[prefix + 'size']*2,
        label = node['label'],
        scaleX = 3,
        scaleY = 1,
        posX = (node[prefix+'x'])/scaleX,
        posY = (node[prefix+'y'])/scaleY,
        posX2 = (node[prefix+'x']-size*1.8),
        posY2 = (node[prefix+'y']),
        fontSize = size*0.8;

    renderer.drawEllipse(context,colorChapter,posX,posY,size,scaleX,scaleY);
    renderer.drawEllipse(context,'#FBF5EF',posX,posY,size*0.95,scaleX,scaleY);

    renderer.drawFont(context, fontSize, label , posX2, posY2);

};
sigma.canvas.nodes.SECTION = function(node, context, settings) {
    var prefix = settings('prefix') || '',
        size = node[prefix + 'size']*1.5,
        label = node['label'],
        scaleX = 3,
        scaleY = 1,
        posX = (node[prefix+'x'])/scaleX,
        posY = (node[prefix+'y'])/scaleY,
        posX2 = (node[prefix+'x']-size*1.5),
        posY2 = (node[prefix+'y']),
        fontSize = size/1.5;

    renderer.drawEllipse(context,colorSection,posX,posY,size,scaleX,scaleY);
    renderer.drawEllipse(context,'#FBF5EF',posX,posY,size*0.9,scaleX,scaleY);


    renderer.drawFont(context, fontSize, label, posX2, posY2);

};
  
  
  return renderer;
});



