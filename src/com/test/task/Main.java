package com.test.task;

import static com.test.task.XsdValidator.validateXMLSchema;
import static com.test.task.XsltTransformer.transform;

public class Main {

    public static void main(String[] args) {
        if (args.length == 4) {
            MyLogger.init();
            boolean resultValidation = validateXMLSchema(args[0], args[1]);
            if (resultValidation) {
               boolean resultTransformation =  transform(args[0], args[2], args[3]);
                 if (resultTransformation)
                     validateXMLSchema(args[3],args[1]);
            }
        } else
            System.out.println("Invalid number of parameters.The program needs 4 parameters");
    }
}
