#------------------------------------------------------------------------------
# Name: William Jung
# Cruzid: 1477612
# Description: This file will be called upon by make in order to compile
#	main and all its files.
# File Name: Makefile
# ------------------------------------------------------------------------------

CSOURCES = $(wildcard *.cpp)
SOURCES  = README Makefile $(CSOURCES)
FLAGS    = -std=c++11 -pthread -Wall
OBJECTS  = $(patsubst %.cpp, %.o, $(CSOURCES))
HEADERS  = $(wildcard *.h)
EXEBIN   = bjsim
CHECK    = valgrind --leak-check=full $(EXEBIN) $(CSOURCES) outfile

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	g++ -o $(EXEBIN) $(OBJECTS)
$(OBJECTS) : $(CSOURCES) $(HEADERS)
	g++ -c $(FLAGS) $(CSOURCES)
clean :
	rm -f $(EXEBIN)  $(OBJECTS)
check:
	$(CHECK)
