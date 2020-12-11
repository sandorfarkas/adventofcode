const fs = require('fs');
fs.readFile('input', (err, data) => {
  if (err) throw err;
  processInput(data);
});

function processInput(input) {
  input = input.toString();
  for (let i = 65; i < 91; i++) {
    let modifiedInput = input;
    const charA = String.fromCharCode(i);
    const charB = String.fromCharCode(i + 32);
    
    modifiedInput = modifiedInput.split(charA).join('');
    modifiedInput = modifiedInput.split(charB).join('');
    console.log(charA, charB, modifiedInput.length, measureReact(modifiedInput));
  }
}

function measureReact(input) {
  let foundOpposite = true;
  loop:
  while (foundOpposite == true) {
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
  return input.length;
}
