package blockchain;

import java.util.Date;

public class Block {
	
	private Integer index;
	private Date timestamp;
	private String transactionData;
	private Integer nonce;
	
	private String hashOfPrevious;
	private String hash;
	
	
	public Block(Integer index, String transactionData) {
		
		this.index = index;
		this.timestamp = new Date();
		this.transactionData = transactionData;
		this.nonce = 0;
		
	}
	
	public void setHashOfPrevious(String hashOfPrevious) {
		this.hashOfPrevious = hashOfPrevious;
	}
	
	public String getHash() {
		return this.hash;
	}
	
	//Hash se racuna uz pomoc vrednosti svih polja klase.
	private String calculateHash() {
		
		//TODO: Using fields of a block calculate the hash.
		return null;
	}
	
	//'Rudarenje' bloka podrazumeva iterativno povecavanje nonce promenljive i izracunavanje hash vrednosti
	//sve dok vrednost hash-a ne pocinje sa {difficulty} brojem nula.
	public void mineBlock(int difficulty) {
		//TODO: In order to mine a block, the nonce field should be
		//incremented until calculated hash has {difficulty} zeroes from the start.
	}
}
