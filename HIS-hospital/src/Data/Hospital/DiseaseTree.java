package Data.Hospital;

import Data.Structure.TreeOverFlowException;

import java.util.Iterator;
import java.util.Vector;

public class DiseaseTree {
    private static final int DEFAULT_MAX = 10;

    private DiseaseNode[] nodes;
    private DiseaseNode root;
    private int firstFree = 0;
    private int size = DEFAULT_MAX;

    public DiseaseTree(int size) {
        this.size = size;
    }

    public void insert(DiseaseNode tempNode) throws TreeOverFlowException {
        if (firstFree == size) {
            throw new TreeOverFlowException("OverFlow!");
        }
        nodes[firstFree] = tempNode;
        // index++;
        if (root == null) {
            root = nodes[firstFree];
        } else {
            DiseaseNode node=
        }
        firstFree++;
    }

    public DiseaseNode fetch(DiseaseNode target,DiseaseNode cursor) {
        //when the tree is empty
        if (root == null) {
            return null;
        }
        Vector<DiseaseNode> diseaseNodes = cursor.getSub_diseases();
        for (int i = 0; i < diseaseNodes.size(); i++) {
            cursor=diseaseNodes.get(i);
            if(cursor.getName().equals(target.getName())){
                return cursor;
            }else{
                fetch(target,cursor);
            }


            }
        }
    }
