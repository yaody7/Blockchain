package BC;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
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
public class Asset extends Contract {
    public static String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550612433806100606000396000f3006080604052600436106100a4576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631dc2ebfc146100a9578063280923e9146100d45780632c3602261461018157806350c92a74146102185780636f0f53c11461025b57806376cdb03b146102b2578063867904b414610309578063b5e2ebc614610356578063e3b266bd14610399578063f721fb24146103e6575b600080fd5b3480156100b557600080fd5b506100be610429565b6040518082815260200191505060405180910390f35b3480156100e057600080fd5b5061013f600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610470565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561018d57600080fd5b506101d6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190505050610df8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561022457600080fd5b50610259600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610f60565b005b34801561026757600080fd5b5061029c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061139d565b6040518082815260200191505060405180910390f35b3480156102be57600080fd5b506102c76113b5565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561031557600080fd5b50610354600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506113da565b005b34801561036257600080fd5b50610397600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611487565b005b3480156103a557600080fd5b506103e4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611d2c565b005b3480156103f257600080fd5b50610427600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611eb6565b005b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905090565b600080600115158573ffffffffffffffffffffffffffffffffffffffff1663cacfdb886040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156104db57600080fd5b505af11580156104ef573d6000803e3d6000fd5b505050506040513d602081101561050557600080fd5b8101908080519060200190929190505050151514156105275760009150610df0565b3373ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff1663714926856040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156105a257600080fd5b505af11580156105b6573d6000803e3d6000fd5b505050506040513d60208110156105cc57600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff161415156106035760009150610df0565b6032600260008773ffffffffffffffffffffffffffffffffffffffff1663798fd1786040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561066d57600080fd5b505af1158015610681573d6000803e3d6000fd5b505050506040513d602081101561069757600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410156106f25760009150610df0565b428573ffffffffffffffffffffffffffffffffffffffff1663fbf9af016040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561075757600080fd5b505af115801561076b573d6000803e3d6000fd5b505050506040513d602081101561078157600080fd5b810190808051906020019092919050505010156107a15760009150610df0565b828573ffffffffffffffffffffffffffffffffffffffff166398e1b4106040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561080657600080fd5b505af115801561081a573d6000803e3d6000fd5b505050506040513d602081101561083057600080fd5b810190808051906020019092919050505010156108505760009150610df0565b8473ffffffffffffffffffffffffffffffffffffffff1663798fd1786040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156108b457600080fd5b505af11580156108c8573d6000803e3d6000fd5b505050506040513d60208110156108de57600080fd5b810190808051906020019092919050505084848773ffffffffffffffffffffffffffffffffffffffff1663fbf9af016040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561095557600080fd5b505af1158015610969573d6000803e3d6000fd5b505050506040513d602081101561097f57600080fd5b8101908080519060200190929190505050610998612017565b808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828152602001945050505050604051809103906000f080158015610a2b573d6000803e3d6000fd5b5090507f1122debb8cd06e62987fec11e58591f3d47e8034f62aa19aadd6384c8bec26ca8573ffffffffffffffffffffffffffffffffffffffff1663798fd1786040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610ab357600080fd5b505af1158015610ac7573d6000803e3d6000fd5b505050506040513d6020811015610add57600080fd5b810190808051906020019092919050505085858873ffffffffffffffffffffffffffffffffffffffff1663fbf9af016040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b5457600080fd5b505af1158015610b68573d6000803e3d6000fd5b505050506040513d6020811015610b7e57600080fd5b81019080805190602001909291905050506000604051808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001821515151581526020019550505050505060405180910390a1828573ffffffffffffffffffffffffffffffffffffffff166398e1b4106040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610c8357600080fd5b505af1158015610c97573d6000803e3d6000fd5b505050506040513d6020811015610cad57600080fd5b81019080805190602001909291905050501115610dec578473ffffffffffffffffffffffffffffffffffffffff1663c3230d0d848773ffffffffffffffffffffffffffffffffffffffff166398e1b4106040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610d4557600080fd5b505af1158015610d59573d6000803e3d6000fd5b505050506040513d6020811015610d6f57600080fd5b8101908080519060200190929190505050036040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610dd357600080fd5b505af1158015610de7573d6000803e3d6000fd5b505050505b8091505b509392505050565b600080338585854201610e09612017565b808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828152602001945050505050604051809103906000f080158015610e9c573d6000803e3d6000fd5b5090507f1122debb8cd06e62987fec11e58591f3d47e8034f62aa19aadd6384c8bec26ca3386868642016000604051808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001821515151581526020019550505050505060405180910390a1809150509392505050565b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548173ffffffffffffffffffffffffffffffffffffffff166398e1b4106040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561100457600080fd5b505af1158015611018573d6000803e3d6000fd5b505050506040513d602081101561102e57600080fd5b810190808051906020019092919050505011151561139a578073ffffffffffffffffffffffffffffffffffffffff166398e1b4106040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156110aa57600080fd5b505af11580156110be573d6000803e3d6000fd5b505050506040513d60208110156110d457600080fd5b8101908080519060200190929190505050600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825403925050819055508073ffffffffffffffffffffffffffffffffffffffff166398e1b4106040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561119557600080fd5b505af11580156111a9573d6000803e3d6000fd5b505050506040513d60208110156111bf57600080fd5b8101908080519060200190929190505050600160008373ffffffffffffffffffffffffffffffffffffffff1663714926856040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561123857600080fd5b505af115801561124c573d6000803e3d6000fd5b505050506040513d602081101561126257600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055508073ffffffffffffffffffffffffffffffffffffffff166364f65cc06040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b15801561131e57600080fd5b505af1158015611332573d6000803e3d6000fd5b505050507f371ae902a58a630b61352d7bdb0cf5ad841f3fc1aabd9b3956eeb05226aa616581604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a15b50565b60026020528060005260406000206000915090505481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561143557611483565b80600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b5050565b6000600115158273ffffffffffffffffffffffffffffffffffffffff1663cacfdb886040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156114f157600080fd5b505af1158015611505573d6000803e3d6000fd5b505050506040513d602081101561151b57600080fd5b81019080805190602001909291905050501515141561153957611d28565b3373ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1663714926856040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156115b457600080fd5b505af11580156115c8573d6000803e3d6000fd5b505050506040513d60208110156115de57600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1614151561161157611d28565b8173ffffffffffffffffffffffffffffffffffffffff166364f65cc06040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b15801561167557600080fd5b505af1158015611689573d6000803e3d6000fd5b505050507f371ae902a58a630b61352d7bdb0cf5ad841f3fc1aabd9b3956eeb05226aa616582604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a18173ffffffffffffffffffffffffffffffffffffffff1663798fd1786040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561175457600080fd5b505af1158015611768573d6000803e3d6000fd5b505050506040513d602081101561177e57600080fd5b81019080805190602001909291905050506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff166398e1b4106040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561181557600080fd5b505af1158015611829573d6000803e3d6000fd5b505050506040513d602081101561183f57600080fd5b81019080805190602001909291905050508473ffffffffffffffffffffffffffffffffffffffff1663fbf9af016040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156118b457600080fd5b505af11580156118c8573d6000803e3d6000fd5b505050506040513d60208110156118de57600080fd5b81019080805190602001909291905050506118f7612017565b808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828152602001945050505050604051809103906000f08015801561198a573d6000803e3d6000fd5b5090507f1122debb8cd06e62987fec11e58591f3d47e8034f62aa19aadd6384c8bec26ca8273ffffffffffffffffffffffffffffffffffffffff1663798fd1786040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015611a1257600080fd5b505af1158015611a26573d6000803e3d6000fd5b505050506040513d6020811015611a3c57600080fd5b81019080805190602001909291905050506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff166398e1b4106040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015611ad357600080fd5b505af1158015611ae7573d6000803e3d6000fd5b505050506040513d6020811015611afd57600080fd5b81019080805190602001909291905050508573ffffffffffffffffffffffffffffffffffffffff1663fbf9af016040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015611b7257600080fd5b505af1158015611b86573d6000803e3d6000fd5b505050506040513d6020811015611b9c57600080fd5b81019080805190602001909291905050506000604051808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838152602001821515151581526020019550505050505060405180910390a18173ffffffffffffffffffffffffffffffffffffffff166398e1b4106040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015611ca057600080fd5b505af1158015611cb4573d6000803e3d6000fd5b505050506040513d6020811015611cca57600080fd5b8101908080519060200190929190505050600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b5050565b80600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541015611d7857611eb2565b80600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254039250508190555080600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055507f570e6727d8aecbd70b2b62942bafdd52df16512778cd1454a51e2e1648719386338383604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390a15b5050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515611f1157612014565b6014600160008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055506014600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825403925050819055506032600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505b50565b6040516103e080612028833901905600608060405234801561001057600080fd5b506040516080806103e083398101806040528101908080519060200190929190805190602001909291908051906020019092919080519060200190929190505050836000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550816002819055508042016003819055506000600460006101000a81548160ff021916908315150217905550505050506102d0806101106000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806364f65cc014610088578063714926851461009f578063798fd178146100f657806398e1b4101461014d578063c3230d0d14610178578063cacfdb88146101a5578063fbf9af01146101d4575b600080fd5b34801561009457600080fd5b5061009d6101ff565b005b3480156100ab57600080fd5b506100b461021c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561010257600080fd5b5061010b610246565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561015957600080fd5b5061016261026f565b6040518082815260200191505060405180910390f35b34801561018457600080fd5b506101a360048036038101908080359060200190929190505050610279565b005b3480156101b157600080fd5b506101ba610283565b604051808215151515815260200191505060405180910390f35b3480156101e057600080fd5b506101e961029a565b6040518082815260200191505060405180910390f35b6001600460006101000a81548160ff021916908315150217905550565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600254905090565b8060028190555050565b6000600460009054906101000a900460ff16905090565b60006003549050905600a165627a7a723058207c4ec6ac4e0e27c6ed46d02da8b6c0b1f7a884d773b4376eb1465736a6b4a6910029a165627a7a72305820d0aa3c4d7bce4595cda32b9c086896d4cc2a23c7ffc49d0e33e6e89e7b82e75e0029";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[],\"name\":\"getBalane\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"loanAddress\",\"type\":\"address\"},{\"name\":\"receiver\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"sendOthersLoan\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"receiver\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"},{\"name\":\"time\",\"type\":\"uint256\"}],\"name\":\"sendLoan\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"loanAddress\",\"type\":\"address\"}],\"name\":\"payLoan\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"grade\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"bank\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"receiver\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"issue\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"loanAddress\",\"type\":\"address\"}],\"name\":\"loan\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"receiver\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"sendCash\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"companyAddress\",\"type\":\"address\"}],\"name\":\"evaluate\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"sent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"amount\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"DDL\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"finished\",\"type\":\"bool\"}],\"name\":\"loanRecord\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"loanAddress\",\"type\":\"address\"}],\"name\":\"finishedRecord\",\"type\":\"event\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GETBALANE = "getBalane";

    public static final String FUNC_SENDOTHERSLOAN = "sendOthersLoan";

    public static final String FUNC_SENDLOAN = "sendLoan";

    public static final String FUNC_PAYLOAN = "payLoan";

    public static final String FUNC_GRADE = "grade";

    public static final String FUNC_BANK = "bank";

    public static final String FUNC_ISSUE = "issue";

    public static final String FUNC_LOAN = "loan";

    public static final String FUNC_SENDCASH = "sendCash";

    public static final String FUNC_EVALUATE = "evaluate";

    public static final Event SENT_EVENT = new Event("sent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOANRECORD_EVENT = new Event("loanRecord", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    public static final Event FINISHEDRECORD_EVENT = new Event("finishedRecord", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> getBalane() {
        final Function function = new Function(
                FUNC_GETBALANE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getBalane(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETBALANE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getBalaneSeq() {
        final Function function = new Function(
                FUNC_GETBALANE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getGetBalaneOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_GETBALANE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> sendOthersLoan(String loanAddress, String receiver, BigInteger amount) {
        final Function function = new Function(
                FUNC_SENDOTHERSLOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(loanAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void sendOthersLoan(String loanAddress, String receiver, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SENDOTHERSLOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(loanAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String sendOthersLoanSeq(String loanAddress, String receiver, BigInteger amount) {
        final Function function = new Function(
                FUNC_SENDOTHERSLOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(loanAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, String, BigInteger> getSendOthersLoanInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SENDOTHERSLOAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple1<String> getSendOthersLoanOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SENDOTHERSLOAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> sendLoan(String receiver, BigInteger amount, BigInteger time) {
        final Function function = new Function(
                FUNC_SENDLOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(time)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void sendLoan(String receiver, BigInteger amount, BigInteger time, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SENDLOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(time)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String sendLoanSeq(String receiver, BigInteger amount, BigInteger time) {
        final Function function = new Function(
                FUNC_SENDLOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(time)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, BigInteger, BigInteger> getSendLoanInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SENDLOAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, BigInteger, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple1<String> getSendLoanOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SENDLOAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> payLoan(String loanAddress) {
        final Function function = new Function(
                FUNC_PAYLOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(loanAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void payLoan(String loanAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PAYLOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(loanAddress)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String payLoanSeq(String loanAddress) {
        final Function function = new Function(
                FUNC_PAYLOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(loanAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getPayLoanInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_PAYLOAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<BigInteger> grade(String param0) {
        final Function function = new Function(FUNC_GRADE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> bank() {
        final Function function = new Function(FUNC_BANK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> issue(String receiver, BigInteger amount) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void issue(String receiver, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String issueSeq(String receiver, BigInteger amount) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getIssueInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ISSUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> loan(String loanAddress) {
        final Function function = new Function(
                FUNC_LOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(loanAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void loan(String loanAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_LOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(loanAddress)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String loanSeq(String loanAddress) {
        final Function function = new Function(
                FUNC_LOAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(loanAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getLoanInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_LOAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> sendCash(String receiver, BigInteger amount) {
        final Function function = new Function(
                FUNC_SENDCASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void sendCash(String receiver, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SENDCASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String sendCashSeq(String receiver, BigInteger amount) {
        final Function function = new Function(
                FUNC_SENDCASH, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(receiver), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getSendCashInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SENDCASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> evaluate(String companyAddress) {
        final Function function = new Function(
                FUNC_EVALUATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(companyAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void evaluate(String companyAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_EVALUATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(companyAddress)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String evaluateSeq(String companyAddress) {
        final Function function = new Function(
                FUNC_EVALUATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(companyAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getEvaluateInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_EVALUATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public List<SentEventResponse> getSentEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SENT_EVENT, transactionReceipt);
        ArrayList<SentEventResponse> responses = new ArrayList<SentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SentEventResponse typedResponse = new SentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registersentEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registersentEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<LoanRecordEventResponse> getLoanRecordEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOANRECORD_EVENT, transactionReceipt);
        ArrayList<LoanRecordEventResponse> responses = new ArrayList<LoanRecordEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LoanRecordEventResponse typedResponse = new LoanRecordEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.DDL = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.finished = (Boolean) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerloanRecordEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOANRECORD_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerloanRecordEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOANRECORD_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<FinishedRecordEventResponse> getFinishedRecordEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FINISHEDRECORD_EVENT, transactionReceipt);
        ArrayList<FinishedRecordEventResponse> responses = new ArrayList<FinishedRecordEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FinishedRecordEventResponse typedResponse = new FinishedRecordEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.loanAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerfinishedRecordEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FINISHEDRECORD_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerfinishedRecordEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FINISHEDRECORD_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class SentEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger amount;
    }

    public static class LoanRecordEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger amount;

        public BigInteger DDL;

        public Boolean finished;
    }

    public static class FinishedRecordEventResponse {
        public Log log;

        public String loanAddress;
    }
}
