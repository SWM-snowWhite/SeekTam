import React from 'react'
import { Route } from 'react-router-dom'

export default function Keyword() {
	return (
		<div>
			<h1>Keyword</h1>
			<Route path='/keyword' element={<Keyword />} />
		</div>
	)
}
