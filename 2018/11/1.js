const cells = Array.from(Array(300), () => new Array(300));
const gridSerial = 18;

function calculatePowerLevels() {
    for (let y = 1; y <= 300; y++) {
        for (let x = 1; x <= 300; x++) {
            let rackId = x + 10;
            let powerLevel = rackId * y;
            powerLevel += gridSerial;
            powerLevel *= rackId;
            powerLevel = Math.floor((powerLevel % 1000) / 100) - 5;
            cells[x - 1][y - 1] = powerLevel;
        }
    }
}

function getSumOfSquareAt(coordX, coordY) {
    let sum = 0;
    for (let y = coordY; y < coordY + 3; y++) {
        for (let x = coordX; x < coordX + 3; x++) {
            sum += cells[x - 1][y - 1];
        }
    }
    console.log(`cx: ${coordX} cy: ${coordY} sum: ${sum}`);
    return sum;
}

function solve() {
    calculatePowerLevels();
    
    let max = 0;
    let maxCoords = {};
    for (let y = 1; y <= 297; y++) {
        for (let x = 1; x <= 297; x++) {
            let sum = getSumOfSquareAt(x, y);
            if (sum > max) {
                max = sum;
                maxCoords = { x: x, y: y};
            }
        }
    }
    console.log(max, maxCoords);
}

solve();
