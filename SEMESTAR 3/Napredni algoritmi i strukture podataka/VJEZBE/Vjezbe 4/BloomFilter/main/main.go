package main

import (
	"bytes"
	"encoding/gob"
	"fmt"
)

func main() {
	fns := CreateHashFunctions(5)

	buf := &bytes.Buffer{}
	encoder := gob.NewEncoder(buf)
	decoder := gob.NewDecoder(buf)

	for _, fn := range fns {
		data := []byte("hello")
		fmt.Printf("hashed value [before hash fn serialization]: %v\n", fn.Hash(data))
		err := encoder.Encode(fn)
		if err != nil {
			panic(err)
		}
		dfn := &HashWithSeed{}
		err = decoder.Decode(dfn)
		if err != nil {
			panic(err)
		}
		fmt.Printf("hashed value [after hash fn deserialization]: %v\n\n", fn.Hash(data))
	}

}
