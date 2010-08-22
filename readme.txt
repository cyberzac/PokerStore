Implementation comments:

This project uses Maven, Spring and Hibernate. 

The use of Spring makes the PokerstoreFactroyImpl obsolete. The com.ongame.pokerstore.test.PokerstoreTest is left untouched. Please look at com.ongame.pokerstore.SpringPokerstoreTest instead.

There is nothing to run execpt the test cases. To run the test cases use:
 
>mvn test

You need a Mysql server running on the local host with a database called pokerstore with a ps user with password ps. This can be changed in the dataSource bean in soulution/src/main/resources/spring.xml. The tables will be automatically created.

To create a deliverable use:

mvn package

The artifacts are under target/pokerstore-1.1-project.{tar.bz2, tar.gz, zip}


Martin Zachrison, zac@cyberzac.se

