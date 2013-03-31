package FanoronaGame;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import FanoronaGame.Commands;

public interface Server implements Commands extends Remote {
	
	
	
	
	//////////THIS IS HOW IT IS SAID TO SET IT UP ONLINE
    public Socket clientSocket = null;
    public ServerSocket serverSocket;
    InputStream sockInput = null;
    OutputStream sockOutput = null;
    byte[] buf; //certain data sent or recieved
    int bytes_read;
    public Server()
    {
        //Set up ServerSocket on port 4001
        try {
            serverSocket = new ServerSocket(4001);
        }
        catch( IOException e ) {
            System.out.println("could not listen on port: 4001");
            System.exit( 1 );
        }
        //listen for Client to connect to server on port 4001
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed");
            System.exit(1);
        }
        /////////////////////////////////////////////////////
        // Sending and recieving data
        try { //reading data
            buf = new byte[1024];
            sockInput = clientSocket.getInputStream();
            bytes_read = sockInput.read(buf, 0, buf.length); //THIS IS HOW YOU READ INFO FROM THE CLIENT
        } catch (IOException e) {
           System.err.println("Failed to Obtain the Input from the Client.");;
        }
        try { //writing data
            buf = new byte[1024];
            sockOutput = clientSocket.getOutputStream();
            sockOutput.write(buf, 0, buf.length); //THIS IS HOW YOU WRITE INFO TO THE CLIENT
        } catch (IOException e) {
            System.err.println("Failed to Obtain the Output from the Client.");

        }
     
        
      }
 

    ////////////////////////////////////////   PART GOT CUT OFF, BUT THIS IS HOW I FOUND TO SET IT UP ON DIFFERENT SITES (FOR TIC TAK TOE) 
        
 /*       
        
        output = new JTextArea();
        getContentPane().add( output, BorderLayout.CENTER );
        output.setText( "Server awaiting connections\n" );
        setSize( 300, 300 );
        show();
    }
    
    // wait for two connections so game can be played
    public void execute()
    {
        for ( int i = 0; i < players.length; i++ ) {
            try {
                players[ i ] =
                new Player( server.accept(), this, i );
                players[ i ].start();
            }
            catch( IOException e ) {
                e.printStackTrace();
                System.exit( 1 );
            }
        }
        // Player 1 is suspended until Player 2 connects.
        // Resume player 1 now.
        synchronized ( players[ 0 ] ) {
            players[ 0 ].threadSuspended = false;
            players[ 0 ].notify();
        }
    }
    
    public void display( String s ) //VOID?
    {
        output.append( s + "\n" ); //OUTPUT.APPEND(NEW FANORONAGAME?)
    }
    // Determine if a move is valid.
    // This method is synchronized because only one move can be
    // made at a time.
    
    public synchronized boolean validMove( int x, int y, int player )
    {
        boolean moveDone = false;
        while ( player != currentPlayer ) {
            try {
                wait();
            }
            catch( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        if ( !isOccupied(x, y) ) {
            board[x][y] = (byte) ( currentPlayer == 0 ? 'B' : 'W' );
            currentPlayer = ( currentPlayer + 1 ) % 2;
            players[ currentPlayer ].otherPlayerMoved( x, y );
            notify(); // tell waiting player to continue
            return true;
        }
        else
            return false;
    }
    
    public boolean isOccupied( int x, int y )
    {
        if ( board[x][y] == 'B' || board [x][y] == 'W' )
            return true;
        else
            return false;
    }
    
    public boolean gameOver()
    {

        // Place code here to test for a winner of the game
        return false;
    }
    
    public static void main( String args[] )
    {
        Server game = new Server();
        game.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e )
            {
                System.exit( 0 );
            }
        });
        
        game.execute();
    }
}
    // Player class to manage each Player as a thread
class Player extends Thread {
    private Socket connection;
    private DataInputStream input;
    private DataOutputStream output;
    private Server control;
    private int number;
    private char mark;
    protected boolean threadSuspended = true;
    public Player( Socket sok, Server srv, int num )
    {
        mark = ( num == 0 ? 'B' : 'W' );
        connection = sok;
        try {
            input = new DataInputStream(connection.getInputStream() );
            output = new DataOutputStream(connection.getOutputStream() );
        }
        catch( IOException e ) {
            e.printStackTrace();
            System.exit( 1 );
        }
        
        control = srv;
        number = num;
    }
    
    public void otherPlayerMoved( int x, int y )
    {
        try {
            String loc;
            loc = (Integer.toString(x) + " " + Integer.toString(y));
            output.writeUTF( "Opponent moved" );
            output.writeChars(loc);
        }
        catch ( IOException e ) { e.printStackTrace(); }
        }
    
        public void run()
        {
            boolean done = false;
            try {
                control.display( "Player " + ( number == 0 ? 'B' : 'W' ) + " connected" );
                output.writeChar( mark );
                output.writeUTF( "Player " + ( number == 0 ? "B connected\n" : "W connected, please wait\n" ) );
            // wait for another player to arrive
            if ( mark == 'B' ) {
            output.writeUTF( "Waiting for another player" );
            try {
                synchronized( this ) {
                    while ( threadSuspended )
                    wait();
                }
            }
            catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            
            output.writeUTF( "Other player connected. Your move." );
            }
            
            // Play game
            while ( !done ) {
                int x = input.readInt();
                int y = input.readInt();
                if( control.validMove( x, y, number )) {
                    control.display( "loc: " + x + "," + y );
                    output.writeUTF( "Valid move." );
                }
                else
                    output.writeUTF( "Invalid move, try again" );
            
                if ( control.gameOver() )
                    done = true;
                }
            connection.close();
            }
            catch( IOException e ) {
                e.printStackTrace();
                System.exit( 1 );
            }
        }
}*/



	public String sendCommand() {
		return "A connection has been established!\n";

	}
	
	public void main(String[] args) {
		try {
			Server gameClient = new Server();
			Commands newConnection = (Commands) UnicastRemoteObject.exportObject(obj, 0);
			Registry registry = LocateRegistry.getRegistry(); 
			registry.bind("Commands", stub);

			System.err.println("Server ready");
		} catch(Exception e) {
			System.err.println("Server exceptoin: " + e.toString());
			e.printStackTrace();
		}
	}
}
