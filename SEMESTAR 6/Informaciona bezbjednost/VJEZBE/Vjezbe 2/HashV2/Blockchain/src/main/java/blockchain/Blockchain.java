package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
	
	private List<Block> blockchain;
	
	public Blockchain() {
		this.blockchain = new ArrayList<>();
		
		Block genesisBlock = createGenesisBlock();
		this.blockchain.add(genesisBlock);
	}
	
	
	//Kreiranje prvog bloka, kojem je hashOfPrevious null.
	public Block createGenesisBlock() {
		Block genesisBlock = new Block(0, "The starting point.");
		return genesisBlock;
	}
	
	public Block getLatestBlock() {
		int indexOfLatest = this.blockchain.size();
		return this.blockchain.get(indexOfLatest-1);
	}
	
	//Kreiranje novog bloka podrazumeva povezivanje prethodnog sa novim, postavljanjem hashOfPrevious vrednosti
	public void addNewBlock(Block block) {
		block.setHashOfPrevious(getLatestBlock().getHash());
		block.mineBlock(0);
		this.blockchain.add(block);
	}
	
	public boolean isChainValid() {
		//TODO: Check validity of the blockchain by checking hash of every
		//block and hash of previous.
		return false;
	}
}
