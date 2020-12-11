let plants = `#..#.#..##......###...###`;
const rulesInput = `...## => #
..#.. => #
.#... => #
.#.#. => #
.#.## => #
.##.. => #
.#### => #
#.#.# => #
#.### => #
##.#. => #
##.## => #
###.. => #
###.# => #
####. => #`;
const ruleLives = [];
const ruleDies = [];
let zeroPotIndex = 0;

function processRules() {
  const ruleRows = rulesInput.split('\n');
  ruleRows.forEach(row => {
    let rule = row.substring(0, 5);
    if (row[9] == '#') {
      ruleLives.push(rule);
    } else {
      ruleDies.push(rule);
    }
  });
}

function calculateNextGeneration() {
  let startIndex = 0;
  let endIndex = plants.length - 1;
  if (plants[0] == '#' || plants.substr(0, 2) == '.#') {
    plants = ".." + plants;
    startIndex = 2;
  }
  if (plants.substring(plants.length - 2) == '#.' || plants[plants.length - 1] == '#') {
    plants = plants + "..";
    endIndex = plants.length - 2;
  }
  
  let newPlants = "";
  for (let i = startIndex; i <= endIndex; i++) {
    let pattern = plants.substring(i - 2, i + 3);
    if (ruleLives.includes(pattern)) {
      newPlants += "#";
    } else {
      newPlants += ".";
    }
  }
  plants = newPlants;
}

function solve() {
  processRules();
  for (let i = 0; i < 20; i++) {
    console.log(plants);
    calculateNextGeneration();
  }  
}

solve();
