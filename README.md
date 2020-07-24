# Bank Simulator

Simulates events of type:
- Customer demographic changes
- Loan Contracts (which generates a Transfer from the Bank to the Customer)
- Transfers (between customers or between the bank or a customer because of a loan contract/payment)

# How to build it

You need to have Java 1.8 and Maven installed on your machine.

Just run the build.sh script.

# How to run it

Look into the run.sh script for an example. It will call the maven generaded jar file passing the right parameters. You can decide:
- How many days you want the simulation to run
- How many events per day
- How many customers the bank has
- If you want to pause the simulation when there is a change of day

Careful: If the numbers are too high, it may require a lot of memory.
