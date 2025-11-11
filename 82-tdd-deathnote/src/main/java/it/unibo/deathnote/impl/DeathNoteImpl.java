package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

/**
 * DeathNoteImpl.
 */
public final class DeathNoteImpl implements DeathNote {

    private static final long AFTERNAME = 40;
    private static final long AFTERDEATH = 6040;

    private final Map<String, Death> person;
    private String last;

    /**
     * Constructor.
     */
    public DeathNoteImpl() {
        this.person = new HashMap<>();
        this.last = "";
    }

    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("The argument passed must be >= 1 and <= the number of rules");
        } else {
            return RULES.get(ruleNumber - 1);
        }
    }

    @Override
    public void writeName(final String name) {
        if (name == null) {
            throw new NullPointerException("The name passed cannot be null"); //NOPMD
        } else {
            this.person.put(name, new Death());
            this.last = name;
        }
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        if (cause == null) {
            throw new IllegalStateException("The cause passed cannot be null");
        } else if (this.person.isEmpty()) {
            throw new IllegalStateException("The Death Note is empty");
        } else {
            return this.person.get(this.last).insertCause(cause);
        }
    }

    @Override
    public boolean writeDetails(final String details) {
        if (details == null) {
            throw new IllegalStateException("The details passed cannot be null");
        } else if (this.person.isEmpty()) {
            throw new IllegalStateException("The Death Note is empty");
        } else {
            return this.person.get(this.last).insertDetails(details);
        }
    }

    @Override
    public String getDeathCause(final String name) {
        if (!this.isNameWritten(name)) {
            throw new IllegalArgumentException("The name passed is not written in this Death Note");
        } else {
            return this.person.get(name).getCause();
        }
    }

    @Override
    public String getDeathDetails(final String name) {
        if (!this.isNameWritten(name)) {
            throw new IllegalArgumentException("The name passed is not written in this Death Note");
        } else {
            return this.person.get(name).getDetails();
        }
    }

    @Override
    public boolean isNameWritten(final String name) {
        return this.person.containsKey(name);
    }

    private static class Death {

        private static final String DEFAULTCAUSE = "heart attack";

        private String cause;
        private String details;
        private final long timeAfterName;
        private long timeAfterDeath;

        Death() {
            this.cause = DEFAULTCAUSE;
            this.details = "";
            this.timeAfterName = System.currentTimeMillis();
            this.timeAfterDeath = System.currentTimeMillis();
        }

        public boolean insertCause(final String causa) {
            if ((System.currentTimeMillis() - this.timeAfterName) <= AFTERNAME) {
                this.cause = causa;
                this.timeAfterDeath = System.currentTimeMillis();
                return true;
            } else {
                this.timeAfterDeath = System.currentTimeMillis();
                return false;
            }
        }

        public boolean insertDetails(final String dettagli) {
            if ((System.currentTimeMillis() - this.timeAfterDeath) <= AFTERDEATH) {
                this.details = dettagli;
                return true;
            } else {
                return false;
            }
        }

        public String getCause() {
            return this.cause;
        }

        public String getDetails() {
            return this.details;
        }
    }
}
