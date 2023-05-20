import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BinomenalHeap<T extends Comparable> {
    ArrayList<BinomenalNode> trees;

    BinomenalHeap(){
        trees = new ArrayList<BinomenalNode>();
    }
    private BinomenalHeap(BinomenalNode node) {
        trees = new ArrayList<BinomenalNode>();
        trees.add(node);
    }

    private BinomenalHeap(ArrayList<BinomenalNode> trees) {
        this.trees = trees;
    }

    public void add(T data) {
        //TODO: make add
        this.merge(new BinomenalHeap(
                new BinomenalNode(data)));
    }

    public boolean has(T data) {
        //TODO: make find
        BinomenalNode res = this.find(data);
        return res!=null;
    }

    private BinomenalNode find(T data) {
        int r;
        for(BinomenalNode i:trees) {
            r = i.data.compareTo(data);
            if(r<0){
                BinomenalNode res = i.find(data);
                if(res!=null) {
                    return res;
                }
            }
            else if(r==0){
                return i;
            }
        }
        return null;
    }

    public void delete(T data) {
        for(int i = 0;i< trees.size();++i) {
            boolean hasData = trees.get(i).pushUp(data);
            if(hasData){
                BinomenalHeap<T> newHeap = new BinomenalHeap<>(trees.get(i).getChilds());
                trees.remove(i);
                this.merge(newHeap);
                return;
            }
        }
        //TODO: make delete
    }

    public void merge(BinomenalHeap AnotherBHeap) {
        //TODO: check merge!

        for(Object tree : AnotherBHeap.trees) {
            trees.add((BinomenalNode) tree);
        }
        trees = (ArrayList<BinomenalNode>) trees.stream().sorted(Comparator.comparingInt((BinomenalNode x) -> x.getHeight())).collect(Collectors.toList());
        int i = 0;
        while( i < trees.size() ) {
            if( i+1 < trees.size() && trees.get( i ).getHeight() == trees.get( i+1 ).getHeight() ) {
                if( i + 2 < trees.size() && trees.get( i+1 ).getHeight() == trees.get( i+2 ).getHeight() ) {
                    ++i;
                }
                else {
                    BinomenalNode max;
                    if(trees.get(i).getData().compareTo(trees.get( i+1 ).getData())>0) {
                        max = trees.get(i);
                        trees.remove(i);
                    }
                    else {
                        max = trees.get(i+1);
                        trees.remove(i+1);
                    }

                    trees.get(i).mergeWith(max);
                }
            }
            else {
                ++i;
            }
        }
    }


    private class BinomenalNode {
        private ArrayList<BinomenalNode> childs;
        T data;

        BinomenalNode(T data){
            childs = new ArrayList<>();
            this.data = data;
        }

        public int getHeight() {
            return childs.size();
        }

        public T getData() {
            return data;
        }

        public void mergeWith(BinomenalNode newChild) {
            if(this.getHeight()==newChild.getHeight()) {
                childs.add(newChild);
            }
            else {
                throw new RuntimeException("Uncorrect child: Parent height is:"+this.getHeight()+" Child height is:"+newChild.getHeight());
            }
        }
        public boolean pushUp(T data){
            int r = this.data.compareTo(data);
            if(r<0){
                boolean res;
                for(int i = 0;i<childs.size();++i) {
                    res = childs.get(i).pushUp(data);
                    if(res){
                        T tmp = childs.get(i).data;
                        childs.get(i).data = this.data;
                        this.data = tmp;
                        return true;
                    }
                }
                return false;
            }
            else {
                return r==0;
            }
        }
        public BinomenalNode find(T data) {
            int r;
            for(BinomenalNode i:childs) {
                r = i.data.compareTo(data);
                if(r<0) {
                    BinomenalNode res = i.find(data);
                    if(res!=null)
                        return res;
                }
                else if(r==0){
                    return i;
                }
            }
            return null;
        }

        public ArrayList<BinomenalNode> getChilds() {
            return childs;
        }
    }
}
