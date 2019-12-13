package BC;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class Loan extends Contract {
    public static String BINARY = "608060405234801561001057600080fd5b506040516080806103e083398101806040528101908080519060200190929190805190602001909291908051906020019092919080519060200190929190505050836000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550816002819055508042016003819055506000600460006101000a81548160ff021916908315150217905550505050506102d0806101106000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806364f65cc014610088578063714926851461009f578063798fd178146100f657806398e1b4101461014d578063c3230d0d14610178578063cacfdb88146101a5578063fbf9af01146101d4575b600080fd5b34801561009457600080fd5b5061009d6101ff565b005b3480156100ab57600080fd5b506100b461021c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561010257600080fd5b5061010b610246565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561015957600080fd5b5061016261026f565b6040518082815260200191505060405180910390f35b34801561018457600080fd5b506101a360048036038101908080359060200190929190505050610279565b005b3480156101b157600080fd5b506101ba610283565b604051808215151515815260200191505060405180910390f35b3480156101e057600080fd5b506101e961029a565b6040518082815260200191505060405180910390f35b6001600460006101000a81548160ff021916908315150217905550565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600254905090565b8060028190555050565b6000600460009054906101000a900460ff16905090565b60006003549050905600a165627a7a723058207c4ec6ac4e0e27c6ed46d02da8b6c0b1f7a884d773b4376eb1465736a6b4a6910029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[],\"name\":\"setFinished\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"getTo\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"getFrom\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"getMoney\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"tempMoney\",\"type\":\"uint256\"}],\"name\":\"setMoney\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"getFinished\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"getDDL\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"tempFr\",\"type\":\"address\"},{\"name\":\"tempTo\",\"type\":\"address\"},{\"name\":\"tempMoney\",\"type\":\"uint256\"},{\"name\":\"tempTime\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_SETFINISHED = "setFinished";

    public static final String FUNC_GETTO = "getTo";

    public static final String FUNC_GETFROM = "getFrom";

    public static final String FUNC_GETMONEY = "getMoney";

    public static final String FUNC_SETMONEY = "setMoney";

    public static final String FUNC_GETFINISHED = "getFinished";

    public static final String FUNC_GETDDL = "getDDL";

    @Deprecated
    protected Loan(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Loan(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Loan(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Loan(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> setFinished() {
        final Function function = new Function(
                FUNC_SETFINISHED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setFinished(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETFINISHED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setFinishedSeq() {
        final Function function = new Function(
                FUNC_SETFINISHED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> getTo() {
        final Function function = new Function(
                FUNC_GETTO, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getTo(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETTO, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getToSeq() {
        final Function function = new Function(
                FUNC_GETTO, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getGetToOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GETTO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> getFrom() {
        final Function function = new Function(
                FUNC_GETFROM, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getFrom(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETFROM, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getFromSeq() {
        final Function function = new Function(
                FUNC_GETFROM, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getGetFromOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GETFROM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> getMoney() {
        final Function function = new Function(
                FUNC_GETMONEY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getMoney(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETMONEY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getMoneySeq() {
        final Function function = new Function(
                FUNC_GETMONEY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getGetMoneyOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GETMONEY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> setMoney(BigInteger tempMoney) {
        final Function function = new Function(
                FUNC_SETMONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempMoney)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void setMoney(BigInteger tempMoney, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SETMONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempMoney)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setMoneySeq(BigInteger tempMoney) {
        final Function function = new Function(
                FUNC_SETMONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempMoney)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getSetMoneyInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETMONEY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> getFinished() {
        final Function function = new Function(
                FUNC_GETFINISHED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getFinished(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETFINISHED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getFinishedSeq() {
        final Function function = new Function(
                FUNC_GETFINISHED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<Boolean> getGetFinishedOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GETFINISHED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> getDDL() {
        final Function function = new Function(
                FUNC_GETDDL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getDDL(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETDDL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getDDLSeq() {
        final Function function = new Function(
                FUNC_GETDDL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getGetDDLOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GETDDL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    @Deprecated
    public static Loan load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Loan(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Loan load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Loan(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Loan load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Loan(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Loan load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Loan(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Loan> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String tempFr, String tempTo, BigInteger tempMoney, BigInteger tempTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(tempFr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(tempTo), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempTime)));
        return deployRemoteCall(Loan.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Loan> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String tempFr, String tempTo, BigInteger tempMoney, BigInteger tempTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(tempFr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(tempTo), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempTime)));
        return deployRemoteCall(Loan.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Loan> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String tempFr, String tempTo, BigInteger tempMoney, BigInteger tempTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(tempFr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(tempTo), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempTime)));
        return deployRemoteCall(Loan.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Loan> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String tempFr, String tempTo, BigInteger tempMoney, BigInteger tempTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(tempFr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(tempTo), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempMoney), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tempTime)));
        return deployRemoteCall(Loan.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
