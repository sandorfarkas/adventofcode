var input = `264, 340
308, 156
252, 127
65, 75
102, 291
47, 67
83, 44
313, 307
159, 48
84, 59
263, 248
188, 258
312, 240
59, 173
191, 130
155, 266
252, 119
108, 299
50, 84
172, 227
226, 159
262, 177
233, 137
140, 211
108, 175
278, 255
259, 209
233, 62
44, 341
58, 175
252, 74
232, 63
176, 119
209, 334
103, 112
155, 94
253, 255
169, 87
135, 342
55, 187
313, 338
210, 63
237, 321
171, 143
63, 238
79, 132
135, 113
310, 294
289, 184
56, 259`;

var coords = [];

input.split('\n').forEach(
  row => {
    var part = row.split(',');
    coords.push({ x: +(part[0].trim()), y: +(part[1].trim()) })
  }
);

function distance(coord1, coord2) {
  return Math.abs(coord1.x - coord2.x) + Math.abs(coord1.y - coord2.y);
}

function findClosest(coord) {
  var closestTarget = coords[0];
  for (target of coords) {
    if (distance(coord, target) < distance(coord, closestTarget)) closestTarget = target;
  }
  var dist = distance(coord, closestTarget);
  for (target of coords) {
    if (target == closestTarget) continue;
    //if (distance(target, coord) == dist) return { x: 0, y: 0 };
  }
  return closestTarget;
}

var minX = coords[0].x;
var maxX = coords[0].x;
var minY = coords[0].y;
var maxY = coords[0].y;

for (coord of coords) {
  if (coord.x < minX) minX = coord.x;
  if (coord.x > maxX) maxX = coord.x;
  if (coord.y < minY) minY = coord.y;
  if (coord.y > maxY) maxY = coord.y;
}
console.log(`minX ${minX} maxX ${maxX} minY ${minY} maxY ${maxY}`);

var c = document.getElementById("myCanvas");
var ctx = c.getContext("2d");

/*
{
  coord: { x: 0, y: 0 },
  closest: { x: 12, y: 12 }
}
*/
var distances = [];
for (var y = minY; y <= maxY; y++) {
  for (var x = minX; x <= maxX; x++) {
    var coord = {x: x, y: y};
    var closest = findClosest(coord);
    distances.push({ coord: coord, closest: closest });
    
    if (coord.x == closest.x && coord.y == closest.y) {
      ctx.strokeStyle = 'black';
    } else {
      ctx.strokeStyle = 'rgb(' + closest.x + ', ' + closest.y + ', 0)';
    }    
    ctx.strokeRect( coord.x, coord.y, 1, 1 );
  }
}

var count = new Map();
var exclude = new Set();
for (distance of distances) {
  if (exclude.has(distance.closest) || distance.closest.x == 0 || distance.closest.y == 0) {
      continue;
  }
  if (distance.coord.x == minX || distance.coord.x == maxX 
    || distance.coord.y == minY || distance.coord.y == maxY ) {
      exclude.add(distance.closest);
      continue;
  }

  if (count.get(distance.closest) == undefined) {
    count.set(distance.closest, 1);
  } else {
    count.set(distance.closest, count.get(distance.closest) + 1);
  }
}

console.log(count);