package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

/**
 * DeathNoteImpl
 */
public class DeathNoteImpl implements DeathNote{

    private Map<String, Death> person = new HashMap<>();

    public DeathNoteImpl() {

    }

    @Override
    public String getRule(int ruleNumber) throws IllegalArgumentException{
        if ((ruleNumber < 1) || (ruleNumber > RULES.size())) {
            throw new IllegalArgumentException("The argument passed must be >= 1 and <= the number of rules");
        }
        else {
            return RULES.get(ruleNumber - 1);
        }
    }

    @Override
    public void writeName(String name) throws NullPointerException{
        if (name.equals(null)) {
            throw new NullPointerException("The name passed cannot be null");
        }
        else {
            this.person.put(name, new Death());
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

    static class Death {
    
        private String cause;
        private String details;

        public Death(final String cause, final String details) {
            this.cause = cause;
            this.details = details;
        }

        public Death() {
            this("heart attack", "");
        }

        //TODO

    }
}