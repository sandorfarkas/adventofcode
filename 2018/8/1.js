const fs = require('fs');
let rootNode;
fs.readFile('testInput', (err, data) => {
  if (err) throw err;
  rootNode = getNodes(data.toString().split(' '));
  //console.log(countTokensInNode(nodes));
  console.log(rootNode);
  console.log(sumOfMetaDataInNode(rootNode));
});

function sumOfMetaDataInNode(node) {
  let sumOfMetadata = node.metadata.reduce((a,b) => +a + +b, 0);
  if (node.children.length != 0) {
    for (children of node.children) {
      sumOfMetadata += sumOfMetaDataInNode(children);
    }
  }
  return sumOfMetadata;
}

function countTokensInNode(node) {
  let tokenCount = node.metadata.length + 2;
  if (node.children.length != 0) {
    for (children of node.children) {
      tokenCount += countTokensInNode(children);
    }
  }
  return tokenCount;
}

function countTokensInSiblingNodes(siblings) {
  let sum = 0;
  for (sibling of siblings) {
    sum += countTokensInNode(sibling);
  }
  return sum;
}

function getNodes(numbers) {
  let node = {};
  node.quantityOfNodes = numbers[0],
  node.quantityOfMetadata = numbers[1]
  node.children = [];
  node.metadata = [];

  for (let i = 0; i < node.quantityOfNodes; i++) {
    let child;
    if (i == 0) {
      child = getNodes(numbers.slice(2));
    } else {
      child = getNodes(numbers.slice(2 + countTokensInSiblingNodes(node.children)));
    }
    node.children.push(child);
  }
  
  const numberOfTokensInChildren = countTokensInSiblingNodes(node.children);
  for (let i = 0; i < node.quantityOfMetadata; i++) {
    node.metadata.push(numbers[i + 2 + numberOfTokensInChildren]);
  }

  /*if (node.quantityOfNodes == 0) {
    for (let i = 0; i < node.quantityOfMetadata; i++) {
      node.metadata.push(numbers[i + 2]);
    }
  }
  if (node.quantityOfNodes == 1) {
    node.leftNode = getNodes(numbers.slice(2));
    for (let i = 0; i < node.quantityOfMetadata; i++) {
      node.metadata.push(numbers[i + 2 + countTokensInNode(node.leftNode)]);
    }
  }
  if (node.quantityOfNodes == 2) {
    node.leftNode = getNodes(numbers.slice(2));
    node.rightNode = getNodes(numbers.slice(2 + countTokensInNode(node.leftNode)));
    for (let i = 0; i < node.quantityOfMetadata; i++) {
      node.metadata.push(numbers[i + 2 + countTokensInNode(node.leftNode) + countTokensInNode(node.rightNode)]);
    }
  }
  //node.childrenNodes = getNodes(numbers.slice(2, numbers.length - node.quantityOfMetadata).join(' '));*/
  return node;
}