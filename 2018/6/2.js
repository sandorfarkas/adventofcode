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

function getDistance(coord1, coord2) {
  return Math.abs(coord1.x - coord2.x) + Math.abs(coord1.y - coord2.y);
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

function getDistanceToAll(coord) {
  var sum = 0;
  for (target of coords) {
    sum += getDistance(coord, target);
  }
  return sum;
}

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
    var distance = getDistanceToAll(coord);
    distances.push({ coord: coord, distance: distance });
  }
}

console.log(distances.length);
console.log(distances.filter(d => d.distance < 10000).length);