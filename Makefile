CSOURCES = $(wildcard src/*.cpp)
SOURCES  = README Makefile $(CSOURCES)
FLAGS    = -std=c++11 -pthread -Wall
OBJECTS  = $(patsubst src/%.cpp, %.o, $(CSOURCES))

HEADERS  = $(wildcard *.h)
EXEBIN   = bjsim
CHECK    = valgrind --leak-check=full $(EXEBIN) $(CSOURCES) outfile

all: $(EXEBIN)


$(EXEBIN) : $(OBJECTS) $(HEADERS)
	g++ -o $(EXEBIN) $(OBJECTS)
	rm -rf $(OBJECTS)

$(OBJECTS) : $(CSOURCES) $(HEADERS)
	g++ -c $(FLAGS) $(CSOURCES) 


clean :
	rm -f $(EXEBIN)
check:
	$(CHECK)

