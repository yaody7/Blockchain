pragma solidity ^0.4.4;

contract Loan{
    
    address fr;
    address to;
    uint money;
    uint DDL;
    bool finished;

   constructor(address tempFr, address tempTo, uint tempMoney, uint tempTime)  {
       fr=tempFr;
       to=tempTo;
       money=tempMoney;
       DDL=now+tempTime;
       finished=false;
    }
    
    function getMoney()public returns(uint){
        return money;
    }
    
    function getFrom() public returns(address){
        return fr;
    }

    function getTo() public returns(address){
        return to;
    }
    
    function getDDL() public returns(uint){
        return DDL;
    }
    
    function getFinished() public returns(bool){
        return finished;
    }
    
    function setFinished() public{
        finished = true;
    }

    function setMoney(uint tempMoney){
	money = tempMoney;
    }
    
}
