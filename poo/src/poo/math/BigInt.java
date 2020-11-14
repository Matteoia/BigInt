package poo.math;
import java.util.Iterator;
import java.util.ListIterator;

public interface BigInt extends Comparable<BigInt>, Iterable<Integer>{
	
	BigInt factory(int x);
	BigInt factory();
	BigInt factory(BigInt b);
	ListIterator<Integer> listIterator();
	
	

	default String value() {
		StringBuilder sb = new StringBuilder(200);
		Iterator<Integer> it = iterator();
		while(it.hasNext()) {
			sb.append(it.next());
		}
		return sb.reverse().toString();
	}/*
	  *Ritorna il valore del BigInt sotto forma di Stringa di caratteri, poich� all'interno della linkedList i numeri sono memorizzati al contrario
	  *dobbiamo invertire il contenuto di sb prima di restituirlo
	  */

	
	
	default int lenght() {
		Iterator<Integer> it = this.iterator();
		int c = 0;
		while(it.hasNext()) {
			it.next();
			c++;
		}
		return c;
	}//Ritorna il numero di cifre di questo BigInt
	
		
	
	default BigInt incr() {
		BigInt ris = factory(1);
		return add(ris);
	}//incrementa di 1 sfruttando il metodo add
	
	
	
	default BigInt decr() {
		if( this.value().equals("0")) throw new IllegalArgumentException();
		BigInt ris = factory(1);
		return sub(ris);
	}//decrementa di 1 sfruttando il metodo sub
	
	
	
	default BigInt add(BigInt a) {
		BigInt ris = factory(0);
		if(a.equals(ris))
			return this;
		if(this.equals(ris)) 
			return a;
		
		ris = factory();
		ListIterator<Integer> it1 = ris.listIterator();
		int compare = this.compareTo(a);
		Iterator<Integer> it2, it3;
		if(compare > 0) {
			it2 = this.iterator(); it3 = a.iterator();
		}else {
			it2 = a.iterator(); it3 = this.iterator();
		}
		Integer quoziente = 0, corrente = 0, resto = 0;
		
		while(it2.hasNext()) {
			if(it3.hasNext()) {
				corrente = it2.next() + it3.next()+quoziente;
			}else {
				corrente = it2.next() + quoziente;
			}
			quoziente = corrente / 10;
			resto = corrente % 10;
			it1.add(resto);
		}
		if(quoziente != 0)
			it1.add(quoziente);
		return ris;
	}
	/*
	 * Inizialmente controlliamo che i due addendi siano entrambi diversi da 0, se cos� non fosse la somma tra un numero e 0 � pari al numero stesso
	 * dunque ritorniamo tale numero. Nel caso in cui i due numeri siano entrambi diversi da 0 procediamo con la somma sfruttando l'algoritmo carta e penna.
	 */
	
	
	
	default BigInt sub(BigInt s) {
		int compare = this.compareTo(s);
		if(compare < 0) throw new IllegalArgumentException();
		BigInt ris = factory(0);
		if(compare == 0)
			return ris;
		
		ris = factory();
		ListIterator<Integer> it1 = ris.listIterator();
		Iterator<Integer> it2 = this.iterator(), it3 = s.iterator();
		boolean riporto = false;
		Integer s1 = 0, s2 = 0,corrente = 0;
		
		while(it2.hasNext()) {
			if(it3.hasNext()) {
				 s2 = it3.next();
				if(riporto) { 
					s1 = it2.next()-1;
					riporto = false;
				}else { 
					s1 = it2.next();
				}
				corrente = s1 - s2;
			}
			else {
				if(riporto) {
					s1 = it2.next()-1;
					riporto = false;
				}else {
					s1 = it2.next();
				}
				corrente = s1;
			}
			if(corrente < 0) {
				it1.add(corrente+10);
				riporto = true;
			}
			else{
				if(!it2.hasNext() && corrente == 0)
					return ris;
				it1.add(corrente);
			}
		}
		return ris;
	} /* 
	   *Prima di iniziare ci assicuriamo che il BigInt this sia maggiore o uguale a s, se This � minore di s solleviamo una eccezione, nel caso in cui this sia uguale a s restiuiamo
	   * 0 senza fare ulteriori calcoli, infine se this � maggiore si s procediamo con l'algoritmo
	   */
	
	
	
	default BigInt mul(BigInt m) {
		BigInt ris = factory(0);
		if(this.equals(ris) || m.equals(ris))
			return ris;
		ris = factory(1);
		if(this.equals(ris))		
			return m;
		if(m.equals(ris))
			return this;
		ris = factory();
		BigInt contatore = this;
		
		while(contatore.compareTo(factory(0)) > 0) {
			ris = ris.add(m);
			contatore = contatore.decr();
		}
		return ris;
	}/*
	  *	Sfruttiamo il metodo add per moltiplicare due BigInt tra di loro
	  */

	
	
	default BigInt div(BigInt d){
		int compare = this.compareTo(d);
		if(compare < 0) throw new IllegalArgumentException();
		if(compare == 0)  return factory(1);
		BigInt quoziente = factory(0);
		BigInt temp = factory(this);
		boolean continua = true;
		
		while(continua) {
			try {
				temp = temp.sub(d);
			}catch(IllegalArgumentException e) {
				return quoziente;
			}
			quoziente = quoziente.incr();
		}
	
		return null;
	}//Ritorna il quoziente della divisione intera tra this e d (this >= d)
	
	
	
	default BigInt rem(BigInt d){
		int compare = this.compareTo(d);
		if(compare < 0) throw new IllegalArgumentException();
		if(compare == 0)  return factory(1);
		BigInt temp = factory(this);
		boolean continua = true;
		
		while(continua) {
			try {
				temp = temp.sub(d);
			}catch(IllegalArgumentException e) {
				return temp;
			}
		}
		return null;
	}//Ritorna il resto della divisione intera tra this e d (thi >= d)
	
	
	
	default BigInt pow(int exp){
		if(exp == 0)
			return factory(1);
		if(exp == 1)
			return this;
		if(exp < 0) throw new IllegalArgumentException();
		
		BigInt ris = factory(this);
		
		while(exp > 1) {
			ris = ris.mul(this);
			exp--;
		}
		return ris;
	}//Calcola la potenza this^exponent (Estremamente lento)
}
