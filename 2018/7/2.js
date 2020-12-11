/*const input = `Step R must be finished before step Y can begin.
Step N must be finished before step T can begin.
Step C must be finished before step L can begin.
Step F must be finished before step B can begin.
Step L must be finished before step D can begin.
Step T must be finished before step D can begin.
Step O must be finished before step E can begin.
Step M must be finished before step Z can begin.
Step A must be finished before step V can begin.
Step K must be finished before step D can begin.
Step W must be finished before step I can begin.
Step B must be finished before step J can begin.
Step H must be finished before step D can begin.
Step P must be finished before step J can begin.
Step J must be finished before step Z can begin.
Step S must be finished before step X can begin.
Step Z must be finished before step U can begin.
Step Y must be finished before step E can begin.
Step V must be finished before step I can begin.
Step U must be finished before step Q can begin.
Step Q must be finished before step D can begin.
Step X must be finished before step I can begin.
Step G must be finished before step E can begin.
Step I must be finished before step D can begin.
Step D must be finished before step E can begin.
Step B must be finished before step S can begin.
Step U must be finished before step E can begin.
Step J must be finished before step G can begin.
Step I must be finished before step E can begin.
Step N must be finished before step G can begin.
Step P must be finished before step Z can begin.
Step X must be finished before step D can begin.
Step H must be finished before step V can begin.
Step Q must be finished before step E can begin.
Step Z must be finished before step D can begin.
Step V must be finished before step D can begin.
Step S must be finished before step Q can begin.
Step F must be finished before step O can begin.
Step F must be finished before step M can begin.
Step W must be finished before step B can begin.
Step J must be finished before step X can begin.
Step G must be finished before step D can begin.
Step R must be finished before step K can begin.
Step L must be finished before step Y can begin.
Step J must be finished before step Q can begin.
Step Z must be finished before step E can begin.
Step Y must be finished before step Q can begin.
Step K must be finished before step P can begin.
Step N must be finished before step B can begin.
Step Q must be finished before step I can begin.
Step P must be finished before step U can begin.
Step F must be finished before step J can begin.
Step L must be finished before step G can begin.
Step Q must be finished before step X can begin.
Step H must be finished before step G can begin.
Step C must be finished before step O can begin.
Step V must be finished before step G can begin.
Step M must be finished before step G can begin.
Step A must be finished before step Z can begin.
Step C must be finished before step A can begin.
Step N must be finished before step P can begin.
Step N must be finished before step L can begin.
Step W must be finished before step E can begin.
Step N must be finished before step U can begin.
Step A must be finished before step U can begin.
Step O must be finished before step G can begin.
Step Y must be finished before step X can begin.
Step P must be finished before step S can begin.
Step Z must be finished before step Q can begin.
Step K must be finished before step S can begin.
Step N must be finished before step Z can begin.
Step Z must be finished before step V can begin.
Step P must be finished before step Y can begin.
Step L must be finished before step I can begin.
Step O must be finished before step P can begin.
Step N must be finished before step A can begin.
Step F must be finished before step A can begin.
Step P must be finished before step E can begin.
Step Z must be finished before step X can begin.
Step O must be finished before step A can begin.
Step F must be finished before step K can begin.
Step T must be finished before step U can begin.
Step Z must be finished before step I can begin.
Step N must be finished before step O can begin.
Step K must be finished before step U can begin.
Step M must be finished before step W can begin.
Step O must be finished before step U can begin.
Step S must be finished before step I can begin.
Step N must be finished before step K can begin.
Step O must be finished before step J can begin.
Step C must be finished before step J can begin.
Step W must be finished before step S can begin.
Step W must be finished before step J can begin.
Step H must be finished before step J can begin.
Step G must be finished before step I can begin.
Step V must be finished before step U can begin.
Step O must be finished before step H can begin.
Step F must be finished before step Y can begin.
Step U must be finished before step D can begin.
Step N must be finished before step E can begin.
Step H must be finished before step P can begin.`;*/

const input = `Step C must be finished before step A can begin.
Step C must be finished before step F can begin.
Step A must be finished before step B can begin.
Step A must be finished before step D can begin.
Step B must be finished before step E can begin.
Step D must be finished before step E can begin.
Step F must be finished before step E can begin.`;

// https://en.wikipedia.org/wiki/Topological_sorting

let edges = [];
const nodes = new Set();

input.split('\n')
.forEach(row => {
 edges.push({
    node1: row[5],
    node2: row[36]
  });
  nodes.add(row[5]);
  nodes.add(row[36]);
});

function hasIncomingEdge(node) {
  let result = false;
  edges.forEach(edge => {
    if (edge.node2 == node) {
      result = true;
    }
  });
  return result;
}

function getNodesWithNoIncomingEdge(nodes) {
  let noIncomingNodes = new Set();
  for (node of nodes) {
    if (!hasIncomingEdge(node)) {
      noIncomingNodes.add(node);
    }
  }
  return noIncomingNodes;
  //return nodes.filter(n => !hasIncomingEdge(n));
}

function getFirstSortedElementFromSet(unorderedSet) {
  let firstLetter = unorderedSet.values().next().value;
  unorderedSet.forEach(letter => {
    if (letter.charCodeAt(0) < firstLetter.charCodeAt(0)) firstLetter = letter;
  });
  return firstLetter;
}

function getConnectedNodes(node) {
  let connectedNodes = new Set();
  edges.forEach(edge => {
    if (edge.node1 == node) {
      connectedNodes.add(edge.node2);
    }
  });
  return connectedNodes;
}

function getWeight(node) {
  //return 60 + node.charCodeAt(0) - 64;
  return 60 + node.charCodeAt(0) - 64;
}

let workers = new Map();

function solve() {
  const sortedElements = []; // L
  let nodesWithNoIncomingEdge =  getNodesWithNoIncomingEdge(nodes); // S
    
  while(nodesWithNoIncomingEdge.size > 0) {
    let node = getFirstSortedElementFromSet(nodesWithNoIncomingEdge);
    nodesWithNoIncomingEdge.delete(node);
    sortedElements.push(node);
    
    let connectedNodes = getConnectedNodes(node);
    connectedNodes.forEach(connectedNode => {
      edges = edges.filter(
        e => !(e.node1 == node && e.node2 == connectedNode)
      );
      
      if (!hasIncomingEdge(connectedNode)) {
        nodesWithNoIncomingEdge.add(connectedNode);
      }
    });
  }
}

solve();
