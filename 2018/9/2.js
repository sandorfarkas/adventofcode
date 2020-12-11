/*
10 players; last marble is worth 1618 points: high score is 8317
13 players; last marble is worth 7999 points: high score is 146373
17 players; last marble is worth 1104 points: high score is 2764
21 players; last marble is worth 6111 points: high score is 54718
30 players; last marble is worth 5807 points: high score is 37305
435 players; last marble is worth 71184 points
*/

const _ = require("lodash")

const ver = _.VERSION
console.log(ver); 
const numberOfPlayers = 435;
const lastMarbleWorth = 7118400;
const playerScores = new Map();
let list = [0];
let currentPosition = 0;

function addMarble(round, player) {
    let marble = round * numberOfPlayers + player
    if (marble % 23 != 0) {
        let newPosition = currentPosition + 2;
        if (newPosition > list.length) {
            newPosition = newPosition - list.length;
        }
        _.splice()
        list.splice(newPosition, 0, marble);
        currentPosition = newPosition;
    } else {
        let score = +marble;
        let newPosition = currentPosition - 7;
        if (newPosition < 0) {
            newPosition = list.length + newPosition;
        }
        score += list[newPosition];
        list.splice(newPosition, 1);
        //spliceOne(list, newPosition);
        currentPosition = newPosition;
        let playerScore = playerScores.get(player);
        if (playerScore == undefined) {
            playerScores.set(player, score);
        } else {
            playerScores.set(player, playerScore + score);
        }
    }
    return marble;
}

function getHighScorePlayer() {
    let max = 0;
    let player;
    for (key of playerScores.keys()) {
        let score = playerScores.get(key);
        if (score > max) {
            max = score;
            player = key;
        }
    }
    console.log(player);
    return max;
}

function getListString() {
    let result = "";
    for (let i = 0; i< list.length; i++) {
        if (i == currentPosition) {
            result += " (" + list[i] + ")";
        } else {
            result += " " + list[i];
        }
    }
    return result;
}

function solve() {
    let round = 0;
    let score = 0;
    while (score != lastMarbleWorth) {
        for (let i = 1; i <= numberOfPlayers; i++) {
            score = addMarble(round, i);
            if (score == lastMarbleWorth) break;
            //console.log(`[${i}] ${getListString()}`);
        }
        round++;
        console.log(score);
    }
    console.log(getHighScorePlayer());
}

solve();
