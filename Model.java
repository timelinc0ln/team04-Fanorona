import java.awt.Container;

/**
 *
 * @author Meg
 */

enum boxstate {
    black,
    white,
    empty
}


public class Model extends Container {
    int x, y; //position
    
    boxstate[][] b;
    
    Pair
       source = new Pair(x, y),
       target = new Pair(x, y);
    
    void move(Pair a, Pair b) {
        source.setFirst(target.getFirst());
        source.setSecond(target.getSecond());
    }
    
    Model(int j, int k){
    }
    
    Pair getDimensions() {
       x = this.getSize().width;
       y = this.getSize().height;
       return new Pair(x,y);
    }
    
    boxstate getstate(int i, int j) {
        return b[i][j];
    }
  
  void select(int i, int j) {
    if (source == null) {
        if(source.getFirst() == i && source.getSecond() == j) {
            source.setFirst(null);
            source.setSecond(null);
        }
        else {
          source.setFirst(i);
          source.setSecond(j);
        }
    }
    else if(source != null) {
      target.setFirst(i);
      target.setSecond(j);
      this.move(source, target);
    }
  
  }

    void intialize() { //only supposed to initialize, no drawing. View does that
      for(int i = 0; i < 9; i++) {
        for(int j = 0; j < 5; j++) {
          // for loop ONE; does the first column
        }
        //for loop TWO; incriments to next column
      }
      //after both for loops
    }
    
    
}
