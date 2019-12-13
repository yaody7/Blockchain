pragma solidity ^0.4.21;
import "./Loan.sol";


contract Asset {
    address public bank;
    mapping (address => uint) private balances;
    mapping (address => uint) public grade;

    event sent(address from, address to, uint amount);
    event loanRecord(address from, address to, uint amount, uint DDL, bool finished);
    event finishedRecord(address loanAddress);

    constructor() {
        bank= msg.sender;
    }

    function issue(address receiver, uint amount) public {
        if (msg.sender != bank) return;
        balances[receiver] += amount;
    }

    function getBalane()public returns(uint){
        return balances[msg.sender];
    }


    function evaluate(address companyAddress) public {
        if (msg.sender != bank) return;
        balances[bank]+=20;
        balances[companyAddress] -=20;
        grade[companyAddress] =50;
    }

    function sendCash(address receiver, uint amount) public {
        if (balances[msg.sender] < amount) return;
        balances[msg.sender] -= amount;
        balances[receiver] += amount;
        emit sent(msg.sender, receiver, amount);
    }
    
    function sendLoan(address receiver, uint amount, uint time) public returns(address) {
	if (grade[msg.sender] < 50) return address(0x0);
        Loan loan = new Loan(msg.sender, receiver, amount, now+time);
        emit loanRecord(msg.sender, receiver, amount, now+time, false);
        return loan;
    }
    
    function sendOthersLoan(address loanAddress,address receiver,uint amount) returns(address){
        if(Loan(loanAddress).getFinished()==true) return address(0x0);
        if(Loan(loanAddress).getTo()!=msg.sender) return address(0x0);
        if(grade[Loan(loanAddress).getFrom()]<50) return address(0x0);
        if(Loan(loanAddress).getDDL()<now) return address(0x0);
        if(Loan(loanAddress).getMoney()<amount) return address(0x0);

        Loan send=new Loan(Loan(loanAddress).getFrom(),receiver,amount,Loan(loanAddress).getDDL());
        emit loanRecord(Loan(loanAddress).getFrom(),receiver,amount,Loan(loanAddress).getDDL(),false);
        if(Loan(loanAddress).getMoney()>amount){
	    Loan(loanAddress).setMoney(Loan(loanAddress).getMoney()-amount);
        }
        return send;
    }
    
    function loan(address loanAddress) public{
        if(Loan(loanAddress).getFinished()==true) return;
        if(Loan(loanAddress).getTo()!=msg.sender) return;
        Loan(loanAddress).setFinished();
        emit finishedRecord(loanAddress);
        Loan loan=new Loan(Loan(loanAddress).getFrom(),bank,Loan(loanAddress).getMoney(),Loan(loanAddress).getDDL());
        emit loanRecord(Loan(loanAddress).getFrom(),bank,Loan(loanAddress).getMoney(),Loan(loanAddress).getDDL(),false);
        balances[msg.sender]+=Loan(loanAddress).getMoney();
    }
    
    function payLoan(address loanAddress) public{
        if(Loan(loanAddress).getMoney()<=balances[msg.sender]){
            balances[msg.sender]-=Loan(loanAddress).getMoney();
            balances[Loan(loanAddress).getTo()]+=Loan(loanAddress).getMoney();
            Loan(loanAddress).setFinished();
            emit finishedRecord(loanAddress);
        }
    }
}
