const fs = require('fs');
fs.readFile('input', (err, data) => {
  if (err) throw err;
  processInput(data);
});

let counter = 0;
function processInput(input) {
  let foundOpposite = true;
  loop:
  while (foundOpposite == true) {
    input = input.toString();
    for (let i = 0; i < input.length-1; i++) {
      const charA = input[i].charCodeAt(0);
      const charB = input[i + 1].charCodeAt(0);
      
      if (Math.abs(charA - charB) == 32) {
        input = input.substring(0, i) + input.substring(i + 2);
        foundOpposite = true;
        continue loop;
      }
    }    
    foundOpposite = false;
  }
  console.log(input.length);
}
