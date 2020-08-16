#-------------------------------------------------------------------------------
# Makefile for sorting algorithms demo
#-------------------------------------------------------------------------------

# compiler flags
WARNINGS = -Xlint:all -Xdoclint:all

# commands
CC       = javac
RM       = rm -f
COMPILE  = $(CC) $(WARNINGS) -d $(BINDIR)
INSTALL  = install

# directories
BINDIR   = ../bin
LOCALBIN = ~/.local/bin

#-- PHONY TARGETS --------------------------------------------------------------

.PHONY: all clean search sort

all: search sort

search: SearchingAlgorithms

sort: SortDriver

clean:
	$(RM) $(BINDIR)/*.class

#-- RULES ----------------------------------------------------------------------

# main classes

SortDriver: SortDriver.java
	$(COMPILE) $^

# units

SortingAlgorithms: SortingAlgorithms.java
	$(COMPILE) $^

SearchingAlgorithms: SearchingAlgorithms.java
	$(COMPILE) $^

