package cmsc256;
import bridges.base.BinTreeElement;
import bridges.connect.Bridges;
import java.util.*;

/***********************************************************************************************************************************************************************************
 * Project5.java
 ************************************************************************************************************************************************************************************
 * project five -expression tree
 ************************************************************************************************************************************************************************************
 * Project description
 * expression tree that would evaluate the equation and get the equation
 * Kevin Phung
 * 4/15/2022
 * CMSC-256
 *********************************************************************************************************************************/



public class Project5 {

    // would build a parse tree with the string expression
    public static bridges.base.BinTreeElement<String> buildParseTree(String expression) {

        // would be the overall tree structure sent
        BinTreeElement<String> parseTree = new BinTreeElement<>("root","");

        //represent as the current part of tree
        BinTreeElement<String> current = parseTree;

        //splitting the white spaces
        String[] expArr = expression.split("\\s+");




        // stack element onto the tree
        Stack<BinTreeElement<String>> exprStack = new Stack<>();
        for (String expArrs: expArr) {
            // would go through the string of the array
            switch (expArrs) {
                // would change current to the left child as it reaches "("
                case "(":
                    current.setLeft(new BinTreeElement<>("e", ""));
                    exprStack.push(current);
                    current = current.getLeft();
                    break;

                // the operators would be the roots
                case "+":
                case "*":
                case "-":
                case "/":
                case "%":
                    current.setLabel(expArrs);
                    // would save the next element right and then would switch "current" to the right
                    current.setRight(new BinTreeElement<>("e", ""));
                    exprStack.push(current);
                    current = current.getRight();
                    break;

                case ")":
                    // would end if the ) is reached and would pop that tree (if it is not empty)
                    if (!exprStack.isEmpty()) {
                        current = exprStack.pop();
                    }
                    break;


                // if it is a operand (number) would then set the # onto parsetree and pop the current element
                // change setting left child with "(" and rightchild with a operator labeling it
                default:
                    try {
                        double number = Double.parseDouble(expArrs);
                    } catch (Exception e){
                        throw new IllegalArgumentException("must be a number");
                    }

                    /*try {
                        double number = Double.parseDouble(expArrs);
                    } catch (Exception e){
                        throw new IllegalArgumentException("must be a number");
                    }*/

                    current.setLabel(expArrs);
                    current = exprStack.pop();
                    break;
            }

        }
        return parseTree;
    }

    // would evaluate the parsetree
    public static double evaluate(bridges.base.BinTreeElement tree) {
        if (tree == null){
            return 0.0;
        }

        if (tree.getLeft() == null && tree.getRight() == null){
            return Double.parseDouble(tree.getLabel());
        }

        // wouuld get the label that has a operator
        if((tree.getLabel().equals("+")) || (tree.getLabel().equals("-"))
                || (tree.getLabel().equals("*")) || (tree.getLabel().equals("/")))
        {
            if(tree.getLabel().equals("+"))
            {
                // return the addition of left and right children
                return evaluate(tree.getLeft()) + evaluate(tree.getRight());
            }
            else if(tree.getLabel().equals("-"))
            {
                // return the subtraction of the right child from the left child
                return evaluate(tree.getLeft()) - evaluate(tree.getRight());
            }
            else if(tree.getLabel().equals("*"))
            {
                // return the multiplication of left and right children
                return evaluate(tree.getLeft()) * evaluate(tree.getRight());
            }
            else if (tree.getLabel().equals("/"))
            {
                // exception since the division by 0 is not allowed
                if (evaluate(tree.getRight()) == 0 ){
                    throw new ArithmeticException("cannot divide by 0");
                }
                // return the division of left and right children
                return evaluate(tree.getLeft()) / evaluate(tree.getRight());
            }

        }

        return 0.0;
    }


    public static String getEquation(bridges.base.BinTreeElement tree) {

        if (tree.getLeft() == null && tree.getRight() == null) {
            return tree.getLabel();
        }
        // would get the equation back
            String result = "";
            result += "( " + getEquation(tree.getLeft()) + " " + tree.getLabel() + " " + getEquation(tree.getRight()) + " )";

        return result;
    }


    public static void main(String[] args) {
        String ex1 = "( ( 7 + 3 ) * ( 5 - 2 ) )";
        BinTreeElement<String> parseTree1 = buildParseTree(ex1);
        double answer1 = evaluate(parseTree1);
        System.out.println(answer1);
        System.out.println(getEquation(parseTree1));

        String ex2 = "( ( 10 + 5 ) * 3 )";
        BinTreeElement<String> parseTree2 = buildParseTree(ex2);
        double answer2 = evaluate(parseTree2);
        System.out.println(answer2);
        System.out.println(getEquation(parseTree2));

        String ex3 = "( ( ( ( ( 2 * 12 ) / 6 ) + 3 ) - 17 ) + ( 2 * 0 ) )";
        BinTreeElement<String> parseTree3 = buildParseTree(ex3);
        double answer3 = evaluate(parseTree3);
        System.out.println(answer3);
        System.out.println(getEquation(parseTree3));

        String ex4 = "( 3 + ( 4 * 5 ) )";
        BinTreeElement<String> parseTree4 = buildParseTree(ex4);
        double answer4 = evaluate(parseTree4);
        System.out.println(answer4);
        System.out.println(getEquation(parseTree4));

        // Initialize a Bridges connection
        Bridges bridges = new Bridges(3, "kevinphung01", "777680704448");

        /* Set an assignment title */
        bridges.setTitle("Arithmetic Parse Tree Project - Debra Duke");
        bridges.setDescription("CMSC 256, Spring 2022");

        try {
            //Tell BRIDGES which data structure to visualize
            bridges.setDataStructure(parseTree1);
            //Visualize the Array
            bridges.visualize();

           // Tell BRIDGES which data structure to visualize *//**//*
           // bridges.setDataStructure(parseTree2);
           // Visualize the Array
            //bridges.visualize();

            //Tell BRIDGES which data structure to visualize *//**//*
            //bridges.setDataStructure(parseTree3);
            // Visualize the Array
            //bridges.visualize();

            //Tell BRIDGES which data structure to visualize *//**//*
            //bridges.setDataStructure(parseTree4);
           //Visualize the Array
            //bridges.visualize();
        } catch (Exception ex) {
            System.out.println("Error connecting to Bridges server.");
        }


    }
}
