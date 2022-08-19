package cmsc256;
import bridges.base.SLelement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
/***********************************************************************************************************************************************************************************
 * MyStack.java
 ************************************************************************************************************************************************************************************
 * project four -MyStack
 ************************************************************************************************************************************************************************************
 * Project description
 * Stack implementations
 * Kevin Phung
 * 4/1/2022
 * CMSC-256
 *********************************************************************************************************************************/

public class MyStack<E> implements cmsc256.StackInterface<E> {

    public SLelement<E> topOfStack = null;

    // stack implementations
    @Override
    public void push(E newEntry) {
        //Check if newEntry is null. If so, throw IllegalArgumentException
        if(newEntry == null) {
            throw new IllegalArgumentException();
        }
        //Create new SL element
        SLelement<E> newNode = new SLelement<>(newEntry);

        //Setting topOfStack value to new element
        newNode.setNext(topOfStack);
        topOfStack = newNode;
    }

    @Override
    public E pop() throws EmptyStackException {
        //Check if the stack is empty. If so, throw EmptyStackException
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        //Create new temp element to hold current topOfStack
        SLelement<E> tempNode = topOfStack;

        //Create new element to get new in stack that will become new topOfStack
        SLelement<E> next = topOfStack.getNext();

        //Setting new topofStack
        topOfStack = next;

        //Returning value held in temp element that was popped off of stack
        return tempNode.getValue();
    }

    @Override
    public E peek() {
        //Check if stack is empty first. If so throw EmptyStackException
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        //Return value to user
        return topOfStack.getValue();
    }

    @Override
    public boolean isEmpty() {
        //Create boolean var and set to default of false
        boolean result = false;
        //Checks if topOfStack is null. If so, sets result to true
        if(topOfStack == null) {
            result = true;
        }
        //Returns result
        return result;
    }

    @Override
    public void clear() {
        //Set topOfStack to null to clear stack
        topOfStack = null;
    }


    // 2nd part of balancing the HTML brackets using method of stacking


    public static boolean isBalanced(File webpage) throws FileNotFoundException {

        //MyStack<Character> stack = new MyStack<>();

        MyStack<String> stack = new MyStack<>();
        Boolean isBalanced = false;
        Scanner in;
        FileReader reader = null;

        // reading the files
        try {
            new FileReader(webpage);
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }

        in = new Scanner(webpage);

        // making a arrayList to store each words of the file into a array
        ArrayList<String> list = new ArrayList<>();
        while (in.hasNext()) {
            list.add(in.next());
        }


        // using stacks and pop and removing each tags
        for (int i = 0; i < list.size(); i++) {

            // conversion of list to char
            String word = list.get(i);

            /*char c = word.charAt(i);*/

            String c = list.get(i);


            // methods of stacks using pop to remove


            // this would store the brackets using pop
            if (c == "<h1>") {
                stack.push("<h1>");
            }
            if (c == "<p>") {
                stack.push("<p>");
            }

            if (c == "<body>") {
                stack.push("<body>");
            }

            if (c == "<html>"){
                stack.push("<html>");
            }


            // using the pop method to remove all the push from the stack
            if (c == "</h1>"){
                if (stack.isEmpty()) {
                    return false;
                }
                if(stack.peek()=="<body>" || stack.peek()=="<html>" || stack.peek()=="<p>"){
                    return false;
                }
                else stack.pop();
            }

            if (c =="</body>"){
                if (stack.isEmpty()) {
                    return false;
                }
                if(stack.peek()=="<h1>" || stack.peek()=="<html>" || stack.peek()=="<p>"){
                    return false;
                }
                else stack.pop();
            }

            if (c =="</p>"){
                if (stack.isEmpty()) {
                    return false;
                }
                if(stack.peek()=="<h1>" || stack.peek()=="<html>" || stack.peek()=="<body>"){
                    return false;
                }
                else
                    stack.pop();
            }

            if (c =="</html>"){
                if (stack.isEmpty()) {
                    return false;
                }
                if(stack.peek()=="<h1>" || stack.peek()=="<body>" || stack.peek()=="<p>"){
                    return false;
                }
                else
                stack.pop();
            }

            // return true if the pop would become the opposite sign
            if (stack.isEmpty()) {
                isBalanced = true;
            }
        }

        return isBalanced;
    }



}



