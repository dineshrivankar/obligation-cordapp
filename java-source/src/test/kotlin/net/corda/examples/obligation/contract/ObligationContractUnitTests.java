package net.corda.examples.obligation.contract;

import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.CordaX500Name;
import net.corda.examples.obligation.Obligation;
import net.corda.testing.core.TestIdentity;
import net.corda.testing.node.MockServices;

import java.util.List;

import static net.corda.finance.Currencies.DOLLARS;
import static net.corda.finance.Currencies.POUNDS;
import static net.corda.testing.node.MockServicesKt.makeTestIdentityService;

/**
 * A base class to reduce the boilerplate when writing obligation contract tests.
 */
abstract class ObligationContractUnitTests {
    protected MockServices ledgerServices = new MockServices(
            ImmutableList.of("net.corda.examples.obligation", "net.corda.testing.contracts"),
            makeTestIdentityService(),
            new TestIdentity(new CordaX500Name("TestIdentity", "", "GB")));
    protected TestIdentity alice = new TestIdentity(new CordaX500Name("Alice", "", "GB"));
    protected TestIdentity bob = new TestIdentity(new CordaX500Name("Bob", "", "GB"));
    protected TestIdentity charlie = new TestIdentity(new CordaX500Name("Bob", "", "GB"));

    protected class DummyState implements ContractState {
        @Override
        public List<AbstractParty> getParticipants() {
            return ImmutableList.of();
        }
    }

    protected class DummyCommand implements CommandData {}

    protected Obligation oneDollarObligation = new Obligation(POUNDS(1), alice.getParty(), bob.getParty());
    protected Obligation tenDollarObligation = new Obligation(DOLLARS(10), alice.getParty(), bob.getParty());
}
