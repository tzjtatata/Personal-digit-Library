package backtable;

public class List {
	private Node Head = null;
	private Node 	Tail = null;
	private Node Pointer = null;
	private int Length = 0;
	public void deleteALL(){
		Head = null;
		Tail = null;
		Pointer = null;
		Length = 0;
	}
	public boolean isEnd(){
		if (Length == 0){
			throw new java.lang.NullPointerException();
		}
		else if (Length == 1){
			return true;
		}
		else {
			return (cursor() == Tail);
		}
	}
	public Node cursor() { 
	  if (Head == null) 
		  throw new java.lang.NullPointerException(); 
	  else if (Pointer == null)  {System.out.println("&");return Head; }
	  else {System.out.println("*");return Pointer;}
	  } 
	public Object currentNode() { 
		Node temp=cursor(); 
		return temp.hashcode; 
	  } 
	public void insert(long d,String file){
		Node e;
		e = find(d);
		//System.out.println("!!!" + e);
		if ( e != null) {
			e.add(file);
			//System.out.println("\n**"+ d +"**\n");
			return ;
		}
		e = new Node(d);
		e.add(file);
		if (Length == 0){
			Tail = e;
			Head = e;
		}
		else {
			if (Tail == null)  Head = e;
			else  {
				Tail.next = e;
				Tail = e;
			}
		}
		Length++;
	}
	public Node find(long da) {
		//System.out.println("**" + Pointer);
		Node temp = Pointer;
		if (Head == null) return null;
		Pointer = Head;
		//System.out.println("**" + Pointer);
		//System.out.println("***" + Pointer.next);
		while (Pointer != null){
			//System.out.println(Pointer.hashcode);
			//System.out.println(da);
			if (Pointer.hashcode  == da) 
			{
				//System.out.println("###");
				return Pointer;
			}
			else {
				Pointer = Pointer.next;
			}
		}
		Pointer = temp;
		return null;
	}
	public String toString() {
		String s = "***";
		Pointer = Head;
		while (Pointer != null) {
			s  = s + '\n' + Pointer.toString();
			Pointer = Pointer.next;
		}
		return s + "\n";
	}
}
