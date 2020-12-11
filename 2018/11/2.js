const cells = Array.from(Array(300), () => new Array(300));
const gridSerial = 2187;

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

function getPowerLevelAt(coords) {
    for (cell of cells) {
        if (cell.x == coords.x && cell.y == coords.y) {
            return cell;
        }
    }
}

function getSumOfSquareAt(coordX, coordY, squareSize) {
    let sum = 0;
    for (let y = coordY; y < coordY + squareSize; y++) {
        for (let x = coordX; x < coordX + squareSize; x++) {
            sum += cells[x - 1][y - 1];
        }
    }
    //console.log(`cx: ${coordX} cy: ${coordY} sum: ${sum}`);
    return sum;
}

function solve() {
    calculatePowerLevels();
    let max = 0;
    let maxCoords = {};
    let maxSquareSize = 0;
    let squareSize = 1;
    while (squareSize <= 300) {
        for (let y = 1; y <= 301 - squareSize; y++) {
            for (let x = 1; x <= 301 - squareSize; x++) {
                let sum = getSumOfSquareAt(x, y, squareSize);
                if (sum > max) {
                    max = sum;
                    maxCoords = { x: x, y: y};
                    maxSquareSize = squareSize;
                }
                console.log(`sum: ${sum} squareSize: ${squareSize}`);
            }
        }
        squareSize++;
    }
    console.log(`${maxCoords.x},${maxCoords.y},${maxSquareSize}`);
}

solve();
