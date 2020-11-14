package poo.math;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class BigIntLL extends AbstractBigInt{
	private LinkedList<Integer> cifre = new LinkedList<>();
	
	
	
	//Costruttori
	public BigIntLL(int x) {
		if(x < 0) throw new IllegalArgumentException();
		String numero = String.valueOf(x);
		for(int i=numero.length()-1; i>=0; i--) {
			cifre.add(Character.digit(numero.charAt(i), 10));
		}
	}//Costruisce il BigInt a partire da un intero, il numero viene memorizzato al contrario per facilitare i metodi di modifica
	public BigIntLL(String numero) {
		if(!(numero.chars().allMatch( Character::isDigit ))) throw new IllegalArgumentException();
		numero = formatta(numero);
		for(int i=numero.length()-1; i>=0; i--) {
			cifre.add(Character.digit(numero.charAt(i), 10));
		}
	}//Costruisce il BigInt a partire da una stringa, il numero viene memorizzato al contrario per facilitare i metodi di modifica
	private String formatta(String numero) {
		if(numero.length() != 1) {
			StringBuilder ris = new StringBuilder();
			boolean inizio = true;
			for(int i=0; i<numero.length(); i++) {
				if(inizio && i!=numero.length()-1) {
					if(numero.charAt(i) != '0') {
						ris.append(numero.charAt(i));
						inizio = false;
					}
				}else {
					ris.append(numero.charAt(i));
				}		
			}
			return ris.toString();
		}
		else {
			return numero;
		}
	}//Ci assicuriamo che quando creiamo un BigInt tramite il costruttore per Stringa questo non abbia zeri all'inizio
	public BigIntLL(BigIntLL big) {
		ListIterator<Integer> it = this.listIterator();
		for(Integer i : big) 
			it.add(i);
	}
	protected BigIntLL() {
		super();
	}
	// !-Costruttori
	
	
	
	//Metodi Factory
	@Override
	public BigIntLL factory(int x) {
		return new BigIntLL(x);
	}
	@Override
	public BigIntLL factory() {
		return new BigIntLL();
	}
	@Override
	public BigIntLL factory(BigInt b) {
		return new BigIntLL((BigIntLL)b);
	}
	// !-Metodi Factory
	
	
	
	// Iteratori
	@Override
	public Iterator<Integer> iterator() {
		return cifre.iterator();
	}
	@Override
	public ListIterator<Integer> listIterator(){
		return cifre.listIterator();
	}
	// !-Iteratori
	
	
	
	// CompareTo
	@Override
	public int compareTo(BigInt o) {
		if(this.lenght() > o.lenght()) return 1;
		if(this.lenght() < o.lenght()) return -1;
		String s1 = this.toString(), s2 = o.toString();
		return s1.compareTo(s2);
	}
	// !-CompareTo
	
	
	// Metodi pi� efficienti rispetto a quelli dell'interfaccia
	@Override
	public int lenght() {
		return cifre.size();
	}
	// !-Metodi pi� efficienti rispetto a quelli dell'interfaccia

	
	
	
	
	public static void main(String[] args) {
		BigIntLL b1 = new BigIntLL("5934920");
		BigIntLL b2 = new BigIntLL("563492093");
		
		System.out.println(b1.mul(b2));
		
	}
}
