package poo.math;

import java.util.Iterator;

public abstract class AbstractBigInt implements BigInt {
	
	public String toString() {
		return this.value();
	}//toString
	
	public boolean equals(Object o) {
		if(!(o instanceof BigInt))
			return false;
		if(o == this)
			return true;
		BigInt b = (BigInt)o;
		if(this.lenght() != b.lenght())
			return false;
		Iterator<Integer> it1 = this.iterator(), it2 = b.iterator();
		while(it1.hasNext()) {
			Integer n1 = it1.next();
			Integer n2 = it2.next();
			if(n1 != n2)
				return false;
		}
		return true;
	}//equals
	
	public int hashCode() {
		final int M = 83;
		int h=0;
		for(Integer i : this)
			h = h*M+i.hashCode();
		return h;
	}//hashCode
	

}
